package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentIssueBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String membershipNo = request.getParameter("membershipNo");
        
        try(Connection con = DBConnection.getConnection()){
            con.setAutoCommit(false);
            PreparedStatement checkStmt = con.prepareStatement("select quantity from book where bookId=?");
            checkStmt.setInt(1, bookId);
            ResultSet rs = checkStmt.executeQuery();
            if((rs.next()) && (rs.getInt("quantity")>0)){
                int quantity = rs.getInt("quantity");
                PreparedStatement insertStmt = con.prepareStatement("insert into issuedbook(bookId,membershipNo,issueDate,dueDate,renewCount) values(?,?,?,?,0)");
                LocalDate today = LocalDate.now();
                LocalDate dueDate = today.plusDays(14);
                insertStmt.setInt(1, bookId);
                insertStmt.setString(2, membershipNo);
                insertStmt.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
                insertStmt.setDate(4, Date.valueOf(dueDate));
                int rowsInserted = insertStmt.executeUpdate();
                System.out.println("Row updated in insert: "+rowsInserted);
                
                PreparedStatement updateStmt = con.prepareStatement("update book set quantity = quantity - 1 where bookId=?");
                updateStmt.setInt(1, bookId);
                updateStmt.executeUpdate();
                con.commit();
                request.setAttribute("message", "Book issued successfully");
            }else{
                request.setAttribute("error", "Book is not available for issuing");
            }
            request.getRequestDispatcher("studentIssueBookResult.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }

}
