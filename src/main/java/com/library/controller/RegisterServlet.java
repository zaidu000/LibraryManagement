package com.library.controller;

import com.library.utility.DBConnection;
import com.library.utility.EmailUtil;
import com.library.utility.PasswordUtil;
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
        String libraryName = request.getParameter("libraryName");
        String address = request.getParameter("address");
        String hashedPassword = null;
        try {
            hashedPassword = PasswordUtil.hashPassword(password);
        } catch (Exception e) {
            e.printStackTrace(); 
            request.setAttribute("error", "Error hashing password");
            request.getRequestDispatcher("registerAdmin.jsp").forward(request, response);
            return;
        }

        String membershipNo = "LIB" + System.currentTimeMillis() % 1000000 + (int) (Math.random() * 1000);

        try (Connection con = DBConnection.getConnection()) {
            if(con == null){
                throw new RuntimeException("Database connection failed!");
            }
            PreparedStatement ps;
            if ("admin".equals(role)) {
                ps = con.prepareStatement("insert into admin(name,libraryName,address,email,password,membershipNo,role) values(?,?,?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, libraryName);
                ps.setString(3, address);
                ps.setString(4, email);
                ps.setString(5, hashedPassword);
                ps.setString(6, membershipNo);
                ps.setString(7, role);
            } else {
                ps = con.prepareStatement("insert into student(name,email,password,membershipNo,role) values(?,?,?,?,?)");
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, hashedPassword);
                ps.setString(4, membershipNo);
                ps.setString(5, role);
            }
            int i = ps.executeUpdate();
            if (i > 0) {
                String subject = "Library Registration successfully";
                String msg = "Hi " + name + ",\n\nYour MembershipNo is: " + membershipNo + "\nPassword: " + password + "\n\nThank You";
                EmailUtil.sendEmail(email, subject, msg);
                request.setAttribute("message", "Registered successfully with email: " + email);
                request.getRequestDispatcher("login.jsp").forward(request, response);
            } else {
                request.setAttribute("error", "Registration failed");
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } catch (Exception e) {
            request.setAttribute("error", "Error: " + e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
