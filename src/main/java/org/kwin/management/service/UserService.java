package org.kwin.management.service;

import org.kwin.management.VO.ResultResponse;
import org.kwin.management.entity.User;

public interface UserService {

    ResultResponse<User> checkUser(String userName, String password);

}
