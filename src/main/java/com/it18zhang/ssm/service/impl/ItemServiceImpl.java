package com.it18zhang.ssm.service.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.service.BaseService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 *
 */
@Service("itemService")
public class ItemServiceImpl extends BaseServiceImpl<Item> implements BaseService<Item> {

    @Resource(name="itemDao")
    public void setDao(BaseDao<Item> dao) {
        super.setDao(dao);
    }
}