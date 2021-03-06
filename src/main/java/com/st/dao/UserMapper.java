package com.st.dao;

import com.st.domain.User;

import java.io.IOException;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> selectList(int offset, int limit);

    int SelectCount();

    public int AddList(List<User> userList) throws IOException;
    public int EditList(List<User> userList);
    public int DelList(String[] id);
}