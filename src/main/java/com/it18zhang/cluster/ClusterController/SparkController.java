package com.it18zhang.cluster.ClusterController;

import com.it18zhang.cluster.Job.SparkJob;
import com.it18zhang.cluster.SSH.SSH;
import com.it18zhang.cluster.SSH.SSHFactory;

/*
这个spark集群需要进行初始化的参数
yarn-cluster 指定固定的executor数
spark-submit \
    --master yarn-cluster \
    --deploy-mode cluster \                  #集群运行模式
    --name wordcount_${date} \               #作业名
    --queue production.group.yanghao \       #指定队列
    --conf spark.default.parallelism=1000 \  #并行度，shuffle后的默认partition数
    --conf spark.network.timeout=1800s \
    --conf spark.yarn.executor.memoryOverhead=1024 \   #堆外内存
    --conf spark.scheduler.executorTaskBlacklistTime=30000 \
    --conf spark.core.connection.ack.wait.timeout=300s \
    --num-executors 200 \                   #executor数目
    --executor-memory 4G \                  #executor中堆的内存
    --executor-cores 2 \                    #executor执行core的数目，设置大于1
    --driver-memory 2G \                    #driver内存，不用过大
    --class ${main_class} \                 #主类
    ${jar_path} \                           #jar包位置
    param_list \                            #mainClass接收的参数列表
yarn-cluster 动态调整的executor数
    spark-submit \
    --master yarn-cluster \
    --deploy-mode cluster \
    --name wordcount_${date} \
    --queue production.group.yanghao \
    --conf spark.dynamicAllocation.enabled=true \     #开启动态分配
    --conf spark.shuffle.service.enabled=true \       #shuffle service，可以保证executor被删除时，shuffle file被保留
    --conf spark.dynamicAllocation.minExecutors=200 \ #最小的executor数目
    --conf spark.dynamicAllocation.maxExecutors=500 \ #最大的executor数目
    --class ${main_class} \
    ${jar_path} \
    param_list
yarn client模式
    spark-shell \
    --master yarn-client \
    --queue production.group.yanghao \      #指定队列
    --num-executors 200 \                   #executor数目
    --executor-memory 4G \                  #executor中堆的内存
    --executor-cores 2 \                    #executor执行core的数目，设置大于1
    --driver-memory 2G \                    #driver内存，不用过大
    --jars ${jar_path}                      #jar包位置
 */
public class SparkController {
    private String command;
    private String ML_HOME;
    public SparkController(){
        this.command="";
        this.ML_HOME="/home/ubuntu/ML";
    }

    //提交算法
    public void submitJar(String src){
        SSHFactory.getSSH().uploadFile(src,this.ML_HOME);
    }
    /**
     * 将这个命令创建，并且添加环境变量，然后传递到集群中
     spark-submit
     --master spark://s201:7077
     --name MyWine
     --class com.it18zhang.scala.ML.LogicRegressWineClassifyDemo
     ML/MLProject-1.0-SNAPSHOT.jar
     hdfs://s101:8020/user/ubuntu/hadoop/sparkdata/winequality-red.csv
     */
    public void runJob(SparkJob job) throws Exception {
        //在这个位置创建命令，
        String command=CommandsFactory.getSparkSubmitCommand(job);
        //在下面执行命令
        SSHFactory.getSSH().executeCommand(command);
    }
}
