package com.it18zhang.cluster.SSH.test;

import com.it18zhang.cluster.SSH.SSH;
import com.it18zhang.cluster.SSH.SSHFactory;
import org.junit.Test;

public class testSSH {
    @Test
    public void upload() throws Exception {
        String datasrcpath="W:\\bigdatalearn\\数据\\wine\\winequality-red1.csv";
        String MLsrcpath="W:\\bigdatalearn\\workspace\\idea-hadoop-4\\ssm\\MLProject\\target\\MLProject-1.0-SNAPSHOT.jar";
        String destpath="";
       SSHFactory.getSSH().uploadFile(MLsrcpath,"/home/ubuntu/ML");
        SSHFactory.getSSH().uploadFile(datasrcpath,"/home/ubuntu/bigdata");
       /*
       spark-submit --master spark://s201:7077 --name MyWine --class com.it18zhang.scala.ML.LogicRegressWineClassifyDemo MLProject-1.0-SNAPSHOT.jar hdfs://s101:8020/user/ubuntu/hadoop/sparkdata/winequality-red.csv

       * */
      //  SSHFactory.getSSH().executeCommand("hdfs dfs -put /home/ubuntu/bigdata /user/ubuntu/hadoop/sparkdata");
    }
}
