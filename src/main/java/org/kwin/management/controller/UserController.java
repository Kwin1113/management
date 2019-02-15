package org.kwin.management.controller;

import org.kwin.management.VO.ResultResponse;
import org.kwin.management.common.Const;
import org.kwin.management.common.CookieConst;
import org.kwin.management.common.RedisConst;
import org.kwin.management.entity.User;
import org.kwin.management.service.UserService;
import org.kwin.management.utils.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("user/login");
    }

    @ResponseBody
    @PostMapping("/checkUser")
    public ModelAndView checkUser(String userName, String password, HttpSession session,
                                  HttpServletResponse httpServletResponse,
                                  Map<String, Object> model) {
        //1.与数据库中的user匹配
        ResultResponse response = userService.checkUser(userName, password);
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        } else {
            model.put("msg", "用户名或密码错误");
            return new ModelAndView("user/login", model);
        }

        //2.设置token至redis
        User user = (User) response.getData();
        String token = UUID.randomUUID().toString();
        Integer expire = RedisConst.EXPIRE;
        redisTemplate.opsForValue().set(String.format(RedisConst.TOKEN_PREFIX, token), user.getUserId().toString(), expire,
                TimeUnit.SECONDS);

        //3.设置token至cookie
        CookieUtil.set(httpServletResponse, CookieConst.TOKEN, token, expire);

        return new ModelAndView("redirect:/product/list");
    }

    @GetMapping("/logout")
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response, Map<String, Object> model) {
        //1.从cookie里查询
        Cookie cookie = CookieUtil.get(request, CookieConst.TOKEN);
        if (cookie != null) {
            //2.清除redis
            redisTemplate.opsForValue().getOperations().delete(String.format(RedisConst.TOKEN_PREFIX, cookie.getValue()));

            //3.清除cookie
            CookieUtil.set(response, CookieConst.TOKEN, null, 0);

        }
        return new ModelAndView("redirect:/user/login");
    }
}
