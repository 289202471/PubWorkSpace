package com.it18zhang.cluster.ClusterController;

import com.it18zhang.cluster.Job.SparkJob;

public class CommandsFactory {
    public static String getSparkSubmitCommand(SparkJob job){
        return EnvironmentVariables.SPARK_HOME_BIN+
                "spark-submit"+
                " --master " +job.getMaster()+
                " --name " +job.getAppName()+
                " --class " +job.getClassName()+
                " " +job.getJarName()+
                " "+job.getDataName();
    }
    //hdfs dfs -put /home/ubuntu/bigdata /user/ubuntu/hadoop/sparkdata
    public static String getHadoopUploadDataCommand(String dataFile){
        return EnvironmentVariables.HADOOP_HOME_BIN+"hdfs dfs -put /home/ubuntu/bigdata/"+dataFile+" /user/ubuntu/hadoop/sparkdata";
    }
}
