package com.it18zhang.cluster.Job;

public class SparkJob {
    String master="";
    String appName="";
    String className="";
    String jarName="";
    String dataName="";

    public SparkJob(String master, String appName, String className, String jarName, String dataName){
        this.master=master;//spark://s201:7077
        this.appName=appName;//MyWine
        this.className=className;//com.it18zhang.scala.ML.LogicRegressWineClassifyDemo
        this.jarName=jarName;//ML/MLProject-1.0-SNAPSHOT.jar
        this.dataName=dataName;// hdfs://s101:8020/user/ubuntu/hadoop/sparkdata/winequality-red.csv
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJarName() {
        return jarName;
    }

    public void setJarName(String jarName) {
        this.jarName = jarName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }




}
