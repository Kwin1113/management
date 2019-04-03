package org.kwin.management.dao.mapper;

import org.apache.ibatis.annotations.Param;
import org.kwin.management.entity.User;

import java.util.List;

public interface UserMapper {

    int deleteByPrimaryKey(Integer userId);

    int insert(User user);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKeySelective(User user);

    int updateByPrimaryKey(User user);

    int insertSelective(User user);

    int checkUserName(String userName);

    User selectLogin(@Param("userName") String userName,@Param("password") String password);
}
