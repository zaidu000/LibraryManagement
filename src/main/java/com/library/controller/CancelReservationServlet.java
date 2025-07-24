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

public class CancelReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String membershipNo = (String)request.getSession().getAttribute("membershipNo");
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("select * from reservation where bookId=? and membershipNo=?");
            ps.setInt(1, bookId);
            ps.setString(2, membershipNo);
            ResultSet rs =  ps.executeQuery();
            if(!rs.next()){
                request.setAttribute("error", "Reservation not found");
                request.getRequestDispatcher("ViewReservationServlet").forward(request, response);
                return;
            }
            PreparedStatement delStmt = con.prepareStatement("delete from reservation where bookId=? and membershipNo=?");
            delStmt.setInt(1, bookId);
            delStmt.setString(2, membershipNo);
            delStmt.executeUpdate();
            request.setAttribute("message", "Reservation cancelled successfully");
            request.getRequestDispatcher("ViewReservationServlet").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
            
    }
}
