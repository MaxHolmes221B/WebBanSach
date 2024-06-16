package com.MaxHolmes.WebBanSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.sql.Date;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.MaxHolmes.WebBanSach.Model.Book;

@Repository
public class BookDAO implements DAO<Book>{
	public List<Book> findAll() {
		List<Book> books = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM book;");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String title = resultSet.getString("title");
				String author = resultSet.getString("author");
				Date date = resultSet.getDate("date");
				int nop = resultSet.getInt("nop");
				String description = resultSet.getString("description");
				int quantity_sold = resultSet.getInt("quantity_sold");
				String pathImg = resultSet.getString("path_img");
				int category_id = resultSet.getInt("category_id");
				books.add(new Book(id, title, author, date, nop, description, quantity_sold, pathImg, category_id));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}

	public Book findById(int id) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Book book = new Book();
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement("SELECT * FROM book WHERE id = ?;");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			while (resultSet.next()) {
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setDate(resultSet.getDate("date"));
				book.setNop(resultSet.getInt("nop"));
				book.setDescription(resultSet.getString("description"));
				book.setQuantity_sold(resultSet.getInt("quantity_sold"));
				book.setPathImg(resultSet.getString("path_img"));
				book.setCategory_id(resultSet.getInt("category_id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return book;
	}

	public int save(Book book, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		int result = 0;
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO book(title, author, date, nop, description, quantity_sold, path_img, category_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setDate(3, book.getDate());
			ps.setInt(4, book.getNop());
			ps.setString(5, book.getDescription());
			ps.setInt(6, book.getQuantity_sold());
			ps.setString(7, book.getPathImg());
			ps.setInt(8, book.getCategory_id());
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	public int update(Book book, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement(
					"UPDATE book SET title = ?, author = ?, date = ?, nop = ?, description = ?, quantity_sold = ?, path_img = ?, category_id = ? WHERE id = ?");
			ps.setString(1, book.getTitle());
			ps.setString(2, book.getAuthor());
			ps.setDate(3, book.getDate());
			ps.setInt(4, book.getNop());
			ps.setString(5, book.getDescription());
			ps.setInt(6, book.getQuantity_sold());
			ps.setString(7, book.getPathImg());
			ps.setInt(8, book.getCategory_id());
			ps.setInt(9, book.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	public int deleteById(int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement("DELETE FROM book WHERE id = ?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
	
	public List<Book> findByTitleAndAuthor(String title, String author) {
	    List<Book> books = new ArrayList<>();
	    Connection connection = null;
	    PreparedStatement ps = null;
	    ResultSet resultSet = null;

	    try {
	        connection = DatabaseUtil.getConnection();
	        ps = connection.prepareStatement("SELECT * FROM book WHERE title = ? AND author = ?");
	        ps.setString(1, title);
	        ps.setString(2, author);
	        resultSet = ps.executeQuery();
	        while (resultSet.next()) {
	            int id = resultSet.getInt("id");
	            String bookTitle = resultSet.getString("title");
	            String bookAuthor = resultSet.getString("author");
	            Date date = resultSet.getDate("date");
	            int nop = resultSet.getInt("nop");
	            String description = resultSet.getString("description");
	            int quantitySold = resultSet.getInt("quantity_sold");
	            String pathImg = resultSet.getString("path_img");
	            int categoryId = resultSet.getInt("category_id");
	            books.add(new Book(id, bookTitle, bookAuthor, date, nop, description, quantitySold, pathImg, categoryId));
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return books;
	}

}
