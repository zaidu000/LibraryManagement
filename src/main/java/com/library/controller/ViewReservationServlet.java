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

public class ViewReservationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String membershipNo = (String)request.getSession().getAttribute("membershipNo");
        if(membershipNo == null){
            request.setAttribute("error","You must logged in to view reservations");
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
            return;
        }
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("select r.bookId, b.name, r.reservedDate from reservation r JOIN Book b on r.bookId = b.bookId where r.membershipNo=? ORDER BY r.reservedDate ASC");
            ps.setString(1, membershipNo);
            ResultSet rs = ps.executeQuery();
            request.setAttribute("reservations", rs);
            request.getRequestDispatcher("studentReservations.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }
}
