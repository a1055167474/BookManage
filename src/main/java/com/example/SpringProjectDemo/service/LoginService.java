package com.example.SpringProjectDemo.service;

import com.example.SpringProjectDemo.common.Response;
import com.example.SpringProjectDemo.entity.User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author chenzihan
 * @description
 * @date 2021/3/31
 */
@Service
public interface LoginService {

    Response<?> doLogin(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response);

    Response<?> logout(HttpSession session, HttpServletRequest request, HttpServletResponse response);

}
