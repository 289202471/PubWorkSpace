package com.it18zhang.cluster.ClusterController;

public class ControllerFacory {
    public static HadoopController getHadoopController(){
        return new HadoopController();
    }
    public static SparkController getSparkController(){
        return new SparkController();
    }
}
