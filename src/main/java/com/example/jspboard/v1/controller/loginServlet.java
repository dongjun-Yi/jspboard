package com.example.jspboard.v1.controller;

import com.example.jspboard.model.Member;
import com.example.jspboard.model.MemberModel;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "loginServlet", urlPatterns = "/login")
public class loginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viewPath = "/WEB-INF/login-form.jsp";
        RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean isValidUser = false;

        for (Member m : MemberModel.members) {
            if (username.equals(m.getUsername()) && password.equals(m.getPassword())) {
                isValidUser = true;

                if (req.getSession().isNew() || req.getSession().getAttribute("username") == null) {
                    req.getSession().setAttribute("username", username);
                }
                String viewPath = "/WEB-INF/post-list.jsp";
                RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
                dispatcher.forward(req, resp);
                break;
            }
        }
        if (!isValidUser) {
            req.setAttribute("loginError", "로그인 정보가 올바르지 않습니다.");
            String viewPath = "/WEB-INF/login-form.jsp";
            RequestDispatcher dispatcher = req.getRequestDispatcher(viewPath);
            dispatcher.forward(req, resp);
        }

    }
}
