package com.it18zhang.cluster.ClusterController;

import com.it18zhang.cluster.SSH.SSHFactory;

public class HadoopController {
    public void uploadDataFile(String dataFilePath) throws Exception {
            SSHFactory.getSSH().uploadFile(dataFilePath,"/home/ubuntu/bigdata");
            String[] dataFiles=dataFilePath.split("/");
            String command=CommandsFactory.getHadoopUploadDataCommand(dataFiles[dataFiles.length-1]);
            SSHFactory.getSSH().executeCommand(command);
    }
}
