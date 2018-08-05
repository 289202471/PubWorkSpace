package com.it18zhang.ssm.dao.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.domain.AlgoModel;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository("algoModelDao")
public class AlgoModelDaoImpl extends SqlSessionDaoSupport implements BaseDao<AlgoModel> {
    public void insert(AlgoModel algoModel) {

    }

    public void update(AlgoModel algoModel) {

    }

    public void delete(Integer id) {

    }

    public AlgoModel selectOne(Integer id) {
        return null;
    }

    public List<AlgoModel> selectAll() {
        return null;
    }

    public List<AlgoModel> selectPage(int offset, int len) {
        return null;
    }

    public int selectCount() {
        return 0;
    }
}
