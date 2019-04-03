package org.kwin.management.service.service;

import org.kwin.management.common.VO.ResultResponse;
import org.kwin.management.entity.User;

public interface UserService {

    ResultResponse<User> checkUser(String userName, String password);

}
