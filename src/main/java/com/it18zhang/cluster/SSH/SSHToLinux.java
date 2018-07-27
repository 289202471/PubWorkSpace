package com.it18zhang.cluster.SSH;

import com.jcraft.jsch.*;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class SSHToLinux {

    public static void main(String[] args) throws IOException, JSchException {
        // TODO Auto-generated method stub
        String host = "192.168.56.101";
        int port = 22;//22号端口是ssh端口
        String user = "ubuntu";
        String password = "1234";
        //目前存在的一个问题就是他无法识别我在集群中配置的hadoop的环境变量，所以我要传递指令必须要将这个指令所在的路径一起传递进去，这个应该可以调整？？？？
        //但是目前不会
//        String command = "whatweb --output-xml http://216.139.147.75:443/";
        //"/soft/hadoop/sbin/start-all.sh\n"
        //"hdfs dfs -ls"
        // "/soft/hadoop/bin/hdfs dfs -ls"
        //"/soft/spark/bin/spark-submit --master local --name MyWordCount --class com.it18zhang.spark.scala.WordCountScala SparkDemo1-1.0-SNAPSHOT.jar /home/centos/test.txt";
        String command="echo $SHELL";
        String res = exeCommand(host,port,user,password,command);
        System.out.println(res);
        System.out.println("--------------shell----------------");
    //    exeCommandCon(host,port,user,password,command);
      // exeCommandShell(host,port,user,password,command);


    }

    public static void exeCommandShell2(String host, int port, String user, String password, String command) throws JSchException, IOException {

        JSch jsch = new JSch();
        //创建对话类，并且使用虚拟机的用户名，连接端口初始化此次回话
        Session session = jsch.getSession(user, host, port);
        if (session == null) {
            System.out.println("create session failed");
        }
        //设置第一次登陆提示（ask|yes|no）
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆密码
        session.setPassword(password);
        //设置连接超时
        session.connect();

        Channel channel = session.openChannel("shell");
        InputStream inputStream = channel.getInputStream();
        OutputStream outputStream = channel.getOutputStream();
        //写命令serverCode
        outputStream.write((command + "\n").getBytes());
        outputStream.flush();
        String out = IOUtils.toString(inputStream, "UTF-8");
        System.out.println(out);
    }

    public static void exeCommandShell(String host, int port, String user, String password, String command) throws JSchException, IOException {

        JSch jsch = new JSch();
        //创建对话类，并且使用虚拟机的用户名，连接端口初始化此次回话
        Session session = jsch.getSession(user, host, port);
        if(session==null){
            System.out.println("create session failed");
        }
        //设置第一次登陆提示（ask|yes|no）
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆密码
        session.setPassword(password);
        //设置连接超时
        session.connect();




        //会话通道用来传递指令
        ChannelShell channelShell=(ChannelShell) session.openChannel("shell");
        //设置输入输出流管道
        PipedInputStream pipeIn=new PipedInputStream();
        PipedOutputStream pipeOut=new PipedOutputStream(pipeIn);
        channelShell.setInputStream(pipeIn);
        channelShell.setOutputStream(System.out);
        channelShell.connect();
        pipeOut.write("ls".getBytes());
        pipeOut.close();
        pipeIn.close();

        channelShell.disconnect();
        session.disconnect();
        //返回
        //return out;
    }
    public static String exeCommand(String host, int port, String user, String password, String command) throws JSchException, IOException {

        JSch jsch = new JSch();
        //创建对话类，并且使用虚拟机的用户名，连接端口初始化此次回话
        Session session = jsch.getSession(user, host, port);
        if(session==null){
            System.out.println("create session failed");
        }
        //设置第一次登陆提示（ask|yes|no）
        session.setConfig("StrictHostKeyChecking", "no");
        //设置登陆密码
        session.setPassword(password);
        //设置连接超时
        session.connect();

        //会话通道用来传递指令
     //   ChannelShell channelShell=(ChannelShell) session.openChannel("shell");
        ChannelExec channelExec = (ChannelExec) session.openChannel("exec");
        //设置输入缓存
        InputStream in = channelExec.getInputStream();
        //设置命令
        channelExec.setCommand(command);
        //设置错误流
        channelExec.setErrStream(System.err);
        //连接并且传参
        channelExec.connect();


        //获取输出
        String out = IOUtils.toString(in, "UTF-8");

        //关闭该关闭的连接
         channelExec.disconnect();
    //    channelShell.disconnect();
        session.disconnect();
        //返回
        return out;
    }

}