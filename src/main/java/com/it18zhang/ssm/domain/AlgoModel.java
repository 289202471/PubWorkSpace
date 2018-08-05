package com.it18zhang.ssm.domain;

/**
 * 相应数据使用相应算法训练出来的模型
 * @author zong
 */
public class AlgoModel {
    private Integer modelID;
    private String modelName;//自己对应的模型的名称
    private String jobID;//自己对应的活动的编号
    private String algoName;//对应的算法的名称
    //private String path;//模型的存储路径(有名称就够了，因为每个模型都会根据用户创建的应用信息，保存到一个系统自动创建的固定目录下)


}
