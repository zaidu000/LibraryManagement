package com.library.controller;

import com.library.model.Book;
import com.library.utility.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SearchBookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("keyword");
        List<Book> searchResults = new ArrayList<>();
        
        try(Connection con = DBConnection.getConnection()){
            String query = "select * from book where name LIKE ? or author LIKE ?";
            PreparedStatement ps = con.prepareStatement(query);
            String keyword = "%" + searchTerm + "%";
            ps.setString(1,keyword);
            ps.setString(2,keyword);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setQuantity(rs.getInt("quantity"));
                book.setParkingSlot(rs.getString("parkingSlot"));
                searchResults.add(book);
            }
            request.setAttribute("searchResults", searchResults);
            request.getRequestDispatcher("searchResult.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("studentDashboard.jsp").forward(request, response);
        }
    }
}
