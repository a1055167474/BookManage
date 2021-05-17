package com.example.SpringProjectDemo.service.impl;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.User;
import com.example.SpringProjectDemo.service.LoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chenzihan
 * @description
 * @date 2021年03月23日
 */


@Service
public class LoginServiceImpl implements LoginService {


    @Override
    public Response<?> doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }

    @Override
    public Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
