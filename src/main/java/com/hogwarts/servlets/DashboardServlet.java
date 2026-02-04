package com.hogwarts.servlets;

import com.hogwarts.dao.DashboardDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "DashboardServlet", value = "/dashboard-servlet")
public class DashboardServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Insere valores nos atributos e envia para o JSP
        req.setAttribute("dashboard", new DashboardDAO().gerarDashboard());
        req.getRequestDispatcher("WEB-INF/dashboard.jsp").forward(req, resp);
    }
}
