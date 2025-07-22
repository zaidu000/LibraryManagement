package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String library = request.getParameter("library");
        String address = request.getParameter("address");
        String membershipNo = role.substring(0, 3).toUpperCase() + System.currentTimeMillis() + (int) (Math.random() * 1000);

        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement ps;
            if ("admin".equals(role)) {
                ps = con.prepareStatement("insert into admin(name,library_name,address,email,role,password,membershipNo) values(?,?,?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, library);
                ps.setString(3, address);
                ps.setString(4, email);
                ps.setString(5, role);
                ps.setString(6, password);
                ps.setString(7, membershipNo);
            } else {
                ps = con.prepareStatement("insert into student(name,email,role,password,membershipNo) values(?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, role);
                ps.setString(4, password);
                ps.setString(5, membershipNo);
            }
            int i = ps.executeUpdate();
            if (i > 0) {
                request.setAttribute("message", "Registered successfully with email: " + email);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registrartion failed");
                request.getRequestDispatcher("register.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }

    }
}
