package com.it18zhang.ssm.service.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.domain.Activity;
import com.it18zhang.ssm.domain.Job;
import com.it18zhang.ssm.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("jobService")
public class JobServiceImpl extends BaseServiceImpl<Job> implements BaseService<Job> {

    @Resource(name="jobDao")
    public void setDao(BaseDao<Job> dao) {
        super.setDao(dao);
    }
}
