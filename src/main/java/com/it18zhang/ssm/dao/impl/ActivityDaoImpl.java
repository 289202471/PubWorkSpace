package com.it18zhang.ssm.dao.impl;

import com.it18zhang.ssm.dao.BaseDao;
import com.it18zhang.ssm.domain.Activity;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("activityDao")
public class ActivityDaoImpl extends SqlSessionDaoSupport implements BaseDao<Activity>{

    public void insert(Activity activity) {
        getSqlSession().insert("activities.insert",activity) ;
    }

    public void update(Activity activity) {

    }

    public void delete(Integer id) {

    }

    public Activity selectOne(Integer id) {
        return getSqlSession().selectOne("activities.selectOne",id);
    }

    public List<Activity> selectAll() {
        return null;
    }

    public List<Activity> selectPage(int offset, int len) {
        return null;
    }

    public int selectCount() {
        return 0;
    }
}
