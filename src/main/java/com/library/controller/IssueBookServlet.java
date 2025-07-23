package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IssueBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("id"));
        String membershipNo = request.getParameter("membershipNo");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("insert into issued_book(id,membershipNo) values(?,?)");
            ps.setInt(1, bookId);
            ps.setString(2, membershipNo);
            ps.executeUpdate();
            response.sendRedirect("viewBook.jsp");
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
