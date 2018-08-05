package com.it18zhang.cluster.Controller.Test;

import com.it18zhang.cluster.ClusterController.ControllerFacory;
import com.it18zhang.cluster.Job.SparkJob;
import org.junit.Test;

public class testCluster {
    @Test
    public void testSpark() throws Exception {
        SparkJob sj=new SparkJob("spark://s201:7077","MyWine",
                "com.it18zhang.scala.ML.LogicRegressWineClassifyDemo",
                "ML/MLProject-1.0-SNAPSHOT.jar",
                "hdfs://s101:8020/user/ubuntu/hadoop/sparkdata/winequality-red.csv");
        ControllerFacory.getSparkController().runJob(sj);
    }
}
