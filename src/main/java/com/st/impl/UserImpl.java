package com.st.impl;

import com.st.dao.UserMapper;
import com.st.domain.User;
import com.st.service.MybatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by stone on 2017/6/26.
 */

public class UserImpl implements UserMapper {
    public int deleteByPrimaryKey(Integer id) {
        try {
            SqlSession session = MybatisUtil.getInstance().openSession();
            int result = session.delete("com.st.dao.UserMapper.deleteByPrimaryKey", id);
            session.commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return -1;
        }
    }

    public int insert(User record) {
        SqlSession session = MybatisUtil.getInstance().openSession();
        int result = session.insert("com.st.dao.UserMapper.insert", record);
        session.commit();
        session.close();
        return result;
    }

    public int insertSelective(User record) {
        return 0;
    }

    public User selectByPrimaryKey(Integer id) {
        SqlSession session = MybatisUtil.getInstance().openSession();
        try {
            User user = session.selectOne("com.st.dao.UserMapper.selectByPrimaryKey", id);
            session.close();
            return user;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public int updateByPrimaryKeySelective(User record) {
        SqlSession session = MybatisUtil.getInstance().openSession();
        try {
            int result = session.update("com.st.dao.UserMapper.updateByPrimaryKeySelective", record);
            session.commit();
            session.close();
            return result;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }

    public int updateByPrimaryKey(User record) {
        return 0;
    }

    public List<User> selectList(int offset,int limit) {
        SqlSession session = MybatisUtil.getInstance().openSession();
        try {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("offset",offset);
            map.put("limit",limit);
            List<User> user = session.selectList("com.st.dao.UserMapper.selectList",map);
            session.close();
            return user;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return null;
        }
    }

    public int SelectCount(){
        SqlSession session = MybatisUtil.getInstance().openSession();
        try {
            int count = session.selectOne("com.st.dao.UserMapper.selectCount");
            session.close();
            return count;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return 0;
        }
    }
}
