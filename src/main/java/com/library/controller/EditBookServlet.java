package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditBookServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String name = request.getParameter("name");
        String author = request.getParameter("author");
        String edition = request.getParameter("edition");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String parkingSlot = request.getParameter("parkingSlot");
        try(Connection con = DBConnection.getConnection()){
            PreparedStatement ps = con.prepareStatement("update book set name=?, author=?, edition=?, quantity=?, parkingSlot=? where bookId=?");
            ps.setString(1, name);
            ps.setString(2, author);
            ps.setString(3,edition);
            ps.setInt(4, quantity);
            ps.setString(5, parkingSlot);
            ps.setInt(6, bookId);
            ps.executeUpdate();
            response.sendRedirect("ViewBookServlet");
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
