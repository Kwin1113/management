package org.kwin.management.service.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.kwin.management.common.VO.ResultResponse;
import org.kwin.management.dao.mapper.UserMapper;
import org.kwin.management.entity.User;
import org.kwin.management.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public ResultResponse<User> checkUser(String userName, String password) {
        int resultCount = userMapper.checkUserName(userName);
        if (resultCount == 0) {
            return ResultResponse.createByErrorMessage("用户名不存在");
        }
        //TODO 密码登陆MD5

        User user = userMapper.selectLogin(userName, password);
        if (user == null) {
            return ResultResponse.createByErrorMessage("密码错误");
        }

        user.setPassword(StringUtils.EMPTY);


        return ResultResponse.createBySuccess("登陆成功", user);
    }
}
