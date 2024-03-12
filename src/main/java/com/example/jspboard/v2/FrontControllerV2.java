package com.example.jspboard.v2;

import com.example.jspboard.v2.controller.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;

@WebServlet(name = "FrontController", urlPatterns = "/v2/*")
public class FrontControllerV2 extends HttpServlet {
    private HashMap<String, ControllerV2> controllerV1Map = new HashMap<>();

    public FrontControllerV2() {
        controllerV1Map.put("/v2", new HomeControllerV2());
        controllerV1Map.put("/v2/login", new LoginControllerV2());
        controllerV1Map.put("/v2/post-add", new PostAddControllerV2());
        controllerV1Map.put("/v2/post-delete", new PostDeleteControllerV2());
        controllerV1Map.put("/v2/post-detail", new PostDetailControllerV2());
        controllerV1Map.put("/v2/post-detail-update", new PostDetailUpdateControllerV2());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        System.out.println(requestURI);

        ControllerV2 controller = controllerV1Map.get(requestURI);

        if (controller == null) {
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        controller.process(req, resp);
    }
}
