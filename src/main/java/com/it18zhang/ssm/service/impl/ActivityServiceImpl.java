package com.it18zhang.ssm.service.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.domain.Activity;
import com.it18zhang.ssm.service.ActivityService;
import com.it18zhang.ssm.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("activityService")
public class ActivityServiceImpl extends BaseServiceImpl<Activity> implements  BaseService<Activity>{
    @Resource(name="activityDao")
    public void setDao(BaseDao<Activity> dao) {
        super.setDao(dao);
    }
}
