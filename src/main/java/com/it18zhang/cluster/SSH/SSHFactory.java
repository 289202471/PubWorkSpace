package com.it18zhang.cluster.SSH;

/**
 * SSH 工厂
 * @author zong
 */
public class SSHFactory {
    public static SSH getSSH(String IP, String hostName, String passWord, int port){
        return new SSH(IP,  hostName,passWord, port);
    }
    public static SSH getSSH(){
        return new SSH();
    }
}
