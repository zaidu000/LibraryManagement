package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RenewBookServlet extends HttpServlet {
    
    private static final int MAX_RENEWAL = 3;
    private static final int RENEW_DAY = 14;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String membershipNo = (String)request.getSession().getAttribute("membershipNo");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps1 = con.prepareStatement("select * from issuedbook where id=? and membershipNo=?");
            ps1.setInt(1, id);
            ps1.setString(2,membershipNo);
            ResultSet rs = ps1.executeQuery();
            if(!rs.next()){
                request.setAttribute("error", "Issued book not found");
                request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
            }
            int bookId = rs.getInt("bookId");
            Date dueDate = rs.getDate("dueDate");
            int renewCount = rs.getInt("renewCount");
            LocalDate currentDue = dueDate.toLocalDate();
            LocalDate today = LocalDate.now();
            if(currentDue.isBefore(today)){
                request.setAttribute("error", "Cannot renew, book is already overdue");
                request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
            }
            if(renewCount >= MAX_RENEWAL){
                request.setAttribute("error", "You reached the maximum renewals");
                request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
            }
            PreparedStatement ps2 = con.prepareStatement("select * from reservation where bookId = ? and membershipNo != ?");
            ps2.setInt(1, bookId);
            ps2.setString(2, membershipNo);
            ResultSet rs2 = ps2.executeQuery();
            if(rs2.next()){
                request.setAttribute("error", "This book is reserved by any other one, you can't be renew this book");
                request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
            }
            LocalDate newDueDate = currentDue.plusDays(RENEW_DAY);
            PreparedStatement ps3 = con.prepareStatement("update issuedbook set dueDate=?, renewCount=renewCount+1 where id=?");
            ps3.setDate(1, Date.valueOf(newDueDate));
            ps3.setInt(2, id);
            ps3.executeUpdate();
            request.setAttribute("message", "Book Renewed Successfully with new due date: "+newDueDate);
            request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
        }
    }

}
