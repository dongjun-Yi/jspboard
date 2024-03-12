package com.example.jspboard.v2.controller;

import com.example.jspboard.model.Member;
import com.example.jspboard.model.MemberModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class LoginControllerV2 implements ControllerV2 {
    @Override
    public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getMethod().equals("GET")) {
            System.out.println("a");
            String viewPath = "/WEB-INF/v2/login-form.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
            dispatcher.forward(req, resp);
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
                    String viewPath = "/WEB-INF/v2/post-list.jsp";
                    RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
                    dispatcher.forward(req, resp);
                    break;
                }
            }
            if (!isValidUser) {
                req.setAttribute("loginError", "로그인 정보가 올바르지 않습니다.");
                String viewPath = "/WEB-INF/v2/login-form.jsp";
                RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
                dispatcher.forward(req, resp);
            }
        }
    }
}
