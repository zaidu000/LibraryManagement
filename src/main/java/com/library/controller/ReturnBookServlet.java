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

public class ReturnBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String membershipNo = (String) request.getSession().getAttribute("membershipNo");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps1 = con.prepareStatement("select * from issuedbook where id=? and membershipNo=?");
            ps1.setInt(1, id);
            ps1.setString(2, membershipNo);
            ResultSet rs1 = ps1.executeQuery();
            if(!rs1.next()){
                request.setAttribute("error", "Issued book not found!!!");
                request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
                return;
            }
            int bookId = rs1.getInt("bookId");
            PreparedStatement ps2 = con.prepareStatement("delete from issuedbook where id=?");
            ps2.setInt(1, id);
            ps2.executeUpdate();
            PreparedStatement ps3 = con.prepareStatement("update book set quantity = quantity+1 where bookId=?");
            ps3.setInt(1, bookId);
            ps3.executeUpdate();
            PreparedStatement ps4 = con.prepareStatement("select * from reservation where bookId=? ORDER BY reservedDate ASC LIMIT 1");
            ps4.setInt(1, bookId);
            ResultSet rs2 = ps4.executeQuery();
            if(rs2.next()){
                String reservedFor = rs2.getString("membershipNo");
                LocalDate today = LocalDate.now();
                LocalDate dueDate = today.plusDays(14);
                PreparedStatement issueBookStmt = con.prepareStatement("insert into issuedbook(bookId, membershipNo, issueDate, dueDate, renewCount) values(?,?,?,?,0)");
                issueBookStmt.setInt(1,bookId);
                issueBookStmt.setString(2, reservedFor);
                issueBookStmt.setDate(3, Date.valueOf(today));
                issueBookStmt.setDate(4, Date.valueOf(dueDate));
                issueBookStmt.executeUpdate();
                
                PreparedStatement delps = con.prepareStatement("delete from reservation where bookId=? and membershipNo=?");
                delps.setInt(1, bookId);
                delps.setString(2, reservedFor);
                delps.executeUpdate();
            }
            
            request.setAttribute("message", "Book returned successfully");
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }
}
