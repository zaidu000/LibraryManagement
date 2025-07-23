package com.library.controller;

import com.library.model.Book;
import com.library.utility.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewBookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Book> books = new ArrayList<>();
        PrintWriter pw = response.getWriter();
        try(Connection con = DBConnection.getConnection()){
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from book");
            while(rs.next()){
                Book book = new Book();
                book.setBookId(rs.getInt("bookId"));
                book.setName(rs.getString("name"));
                book.setAuthor(rs.getString("author"));
                book.setEdition(rs.getString("edition"));
                book.setQuantity(rs.getInt("quantity"));
                book.setParkingSlot(rs.getString("parkingSlot"));
                books.add(book);
//                pw.println("<html>");
//                pw.println("<body>");
//                pw.println("List of book is: "+books);
//                pw.println("</body>");
//                pw.println("</html>");
            }
            request.setAttribute("books", books);
            request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
        }catch(Exception e){
            request.setAttribute("error", "Error: "+e.getMessage());
            request.getRequestDispatcher("adminDashboard.jsp").forward(request, response);
        }
    }
}
