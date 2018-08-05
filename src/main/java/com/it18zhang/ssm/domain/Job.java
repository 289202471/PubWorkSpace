package com.it18zhang.ssm.domain;

import java.util.List;

/**
 * 用户创建的应用（作业）
 * @author zong
 */
public class Job {
    private Integer jobID;//应用id，这个也可以作为主键
    private String appName;//应用名称，用户自己起的
    private Integer userid;//这个job属于那个用户
    private String srcDatafile;//源数据文件路径
    private String dataFrame;//格式化文件路径
    private List<Activity> activities;//用户在这个app中创建的活动的集合

}
