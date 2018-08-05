package com.it18zhang.ssm.dao.impl;

import com.it18zhang.ssm.dao.BaseDao;

import com.it18zhang.ssm.domain.Job;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("jobDao")
public class JobDaoImpl extends SqlSessionDaoSupport implements BaseDao<Job> {

    public void insert(Job job) {

    }

    public void update(Job job) {

    }

    public void delete(Integer id) {

    }

    public Job selectOne(Integer id) {
        return null;
    }

    public List<Job> selectAll() {
        return null;
    }

    public List<Job> selectPage(int offset, int len) {
        return null;
    }

    public int selectCount() {
        return 0;
    }
}
