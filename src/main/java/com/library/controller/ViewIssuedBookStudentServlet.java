package com.library.controller;

import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewIssuedBookStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        String membershipNo = (String)session.getAttribute("membershipNo");
        
        List<Map<String,String>> issuedBooks = new ArrayList<>();
        try(Connection con = DBConnection.getConnection()){
            String sql = "select i.id, b.name, b.author, i.issueDate, i.dueDate, i.renewCount " + "from issuedbook i JOIN Book b on i.bookId=b.bookId " + "where i.membershipNo=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, membershipNo);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Map<String,String> book = new HashMap<>();
                book.put("id", String.valueOf(rs.getInt("id")));
                book.put("name", rs.getString("name"));
                book.put("author",rs.getString("author"));
                Timestamp issueTimestamp = rs.getTimestamp("issueDate");
                book.put("issueDate", issueTimestamp.toString());
                Timestamp dueTimestamp = rs.getTimestamp("dueDate");
                book.put("dueDate", dueTimestamp.toLocalDateTime().toLocalDate().toString());
                book.put("renewCount", String.valueOf(rs.getInt("renewCount")));
                issuedBooks.add(book);
            }
            request.setAttribute("issuedBooks", issuedBooks);
            request.getRequestDispatcher("viewAndRenewIssuedBook.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }

}
