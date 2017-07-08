package com.st.impl;

import com.st.dao.SchoolMapper;
import com.st.domain.School;
import com.st.service.MybatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * Created by stone on 2017/6/28.
 */
public class SchoolImpl implements SchoolMapper {
    public int deleteByPrimaryKey(Integer id) {
        return 0;
    }

    public int insert(School record) {
        return 0;
    }

    public int insertSelective(School record) {
        return 0;
    }

    public School selectByPrimaryKey(Integer id) {
        return null;
    }

    public int updateByPrimaryKeySelective(School record) {
        return 0;
    }

    public int updateByPrimaryKey(School record) {
        return 0;
    }

    public List<School> selectSchoolList(){
        SqlSession session = MybatisUtil.getInstance().openSession();
        List<School> schools = session.selectList("com.st.dao.SchoolMapper.selectList");
        session.close();
        return schools;
    }
}
