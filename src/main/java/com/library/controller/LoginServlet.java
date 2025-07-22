package com.library.controller;

import com.library.utility.DBConnection;
import com.library.utility.PasswordUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String membershipNo = request.getParameter("membershipNo");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        try(Connection con = DBConnection.getConnection()){
            String hashedPassword = PasswordUtil.hashPassword(password);
            String table = "admin".equals(role) ? "admin" : "student";
            String query = "select * from " + table + " where membershipNo=? and password=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, membershipNo);
            ps.setString(2, hashedPassword);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                HttpSession session = request.getSession();
                session.setAttribute("membershipNo", membershipNo);
                session.setAttribute("role", role);
                session.setAttribute("name", rs.getString("name"));
                if("admin".equals(role)){
                    response.sendRedirect("adminDashboard.jsp");
                }else{
                    response.sendRedirect("studentDashboard.jsp");
                }
            }else{
                request.setAttribute("error", "Invalid membershipNo or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }
}
