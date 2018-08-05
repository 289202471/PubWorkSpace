package com.it18zhang.ssm.domain;

/**
 * 每个用户应用中不同算法对应的不同活动
 * @author zong
 */
public class Activity {
    private Integer activityID;//活动主键，
    private String activityName;//作业名称，我们生成的，用来调度集群的时候使用,使用所属的应用名称加活动编号生成。
    private String jobID;//属于哪个作业
    private Integer num;//这个活动就不需要名称了，有个编号就可以了，在某个应用中对应几号活动。
    private String algoName;//算法名称(算法都保存在一个固定的路径下，就不需要algo数据表了)
    private String state;//这个活动的状态，判断是否训练预测结束，由于训练过程和主程序是兵法云行的所以需要多进程编程。
   // Cluster Info;//这里由于可能需要使用到运行过程中这个工作的相关参数，所以这个用来返回集群的信息。这个不应该添加到数据结构里，应该在接口中直接获取，因为怕忘了，先写在这里。
}
