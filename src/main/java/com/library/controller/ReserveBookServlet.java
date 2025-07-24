package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReserveBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String membershipNo = (String)request.getSession().getAttribute("membershipNo");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from reservation where bookId=? and membershipNo=?");
            ps.setInt(1, bookId);
            ps.setString(2, membershipNo);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                request.setAttribute("error", "You have already reserved this book");
            }else{
                PreparedStatement ps2 = con.prepareStatement("insert into reservation(bookId, membershipNo, reservedDate) values(?,?,NOW())");
                ps2.setInt(1, bookId);
                ps2.setString(2, membershipNo);
                ps2.executeUpdate();
                request.setAttribute("message","Book reserved successfully");
            }
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }
}
