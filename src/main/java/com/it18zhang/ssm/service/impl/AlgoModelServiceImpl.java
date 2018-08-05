package com.it18zhang.ssm.service.impl;


import com.it18zhang.ssm.dao.BaseDao;

import com.it18zhang.ssm.domain.AlgoModel;
import com.it18zhang.ssm.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service("algoModelService")
public class AlgoModelServiceImpl extends BaseServiceImpl<AlgoModel> implements BaseService<AlgoModel> {
    @Resource(name="algoModelDao")
    public void setDao(BaseDao<AlgoModel> dao) {
        super.setDao(dao);
    }
}
