package com.it18zhang.cluster.SSH;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import org.apache.commons.io.IOUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * use SSH to connect the master
 * @author zong
 */

public class SSH {
    private String hostIP;//master IP
    private String hostName;//username
    private String passWord;//password
    private int port;//connect port
    JSch jsch;
    Session session;


    public SSH(){
        jsch=new JSch();
        this.hostIP="192.168.56.100";
        this.hostName="ubuntu";
        this.passWord="1234";
        this.port=22;
    }

    public SSH(String IP, String hostName, String passWord, int port){
        jsch=new JSch();
        this.hostIP=IP;
        this.hostName=hostName;
        this.passWord=passWord;
        this.port=port;
    }

    public boolean connect() throws Exception {
        session=jsch.getSession(this.hostName,this.hostIP,this.port);
        if(session==null){
            System.out.println("Connect error");
            return false;
        }
        //set login tips
        session.setConfig("StrictHostKeyChecking", "no");
        //set password
        session.setPassword(this.passWord);
        //set time out
        session.connect();
        return true;
    }

    //turn commands in pipe into commands list
    public List<String> processPipeCommand(String pipeCommand){
        return null;
    }
    //send commands into master
    public List<String> executeCommand(String command) throws Exception {
            this.connect();
            List<String> out=new ArrayList<String>();
            List<String> commands=new ArrayList<String>();
            //judge pipe commands
            if(command.contains("|")){
                //divide pipe commands into commands
                commands=processPipeCommand(command);
            }
            else{
                //put one command into list
                commands.add(command);
            }
        for(int i=0;i<commands.size();i++){
            //create Channel and set using style
            ChannelExec channelExec = (ChannelExec) this.session.openChannel("exec");
            //set input stream
            InputStream in = channelExec.getInputStream();
            //sent commands to cluster
            channelExec.setCommand(commands.get(i));
            //set error log
            channelExec.setErrStream(System.err);
            //connect to the cluster and execute commands
            channelExec.connect();
            //get the result and change it into string
            out.add(IOUtils.toString(in, "UTF-8"));
            //close the connect
            channelExec.disconnect();
            this.session.disconnect();
        }
        return out;
    }
    //ftp upload
    public boolean uploadFile(String src,String dest)  {
        try {
        this.connect();
        //src : upload what,dst : upload to where
        ChannelSftp channelSftp= null;
        channelSftp = (ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        channelSftp.put(src, dest);
        channelSftp.quit();
        } catch (Exception e) {
            e.printStackTrace();
            this.session.disconnect();
            return false;
        }
        this.session.disconnect();
        return true;
    }
    //ftp download
    public boolean downloadFile(String src,String dest)  {
        try {
        this.connect();
        //src download from where，dst download to where
        ChannelSftp channelSftp=(ChannelSftp) session.openChannel("sftp");
        channelSftp.connect();
        channelSftp.get(src, dest);
        channelSftp.quit();
        } catch (Exception e) {
        e.printStackTrace();
        this.session.disconnect();
        return false;
       }
        this.session.disconnect();
        return true;
    }
}
