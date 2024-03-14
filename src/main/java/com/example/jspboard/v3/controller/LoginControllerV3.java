package com.example.jspboard.v3.controller;

import com.example.jspboard.model.Member;
import com.example.jspboard.model.MemberModel;
import com.example.jspboard.MyView;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginControllerV3 implements ControllerV3 {
    @Override
    public MyView process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            return new MyView("/WEB-INF/v3/login-form.jsp");
        } else if (req.getMethod().equals("POST")) {
            String username = req.getParameter("username");
            String password = req.getParameter("password");
            boolean isValidUser = false;

            for (Member m : MemberModel.members) {
                if (username.equals(m.getUsername()) && password.equals(m.getPassword())) {
                    isValidUser = true;

                    if (req.getSession().isNew() || req.getSession().getAttribute("username") == null) {
                        req.getSession().setAttribute("username", username);
                    }
                    return new MyView("/WEB-INF/v3/post-list.jsp");
                }
            }
            if (!isValidUser) {
                req.setAttribute("loginError", "로그인 정보가 올바르지 않습니다.");
                return new MyView("/WEB-INF/v3/login-form.jsp");
            }
        }
        return null;
    }
}
