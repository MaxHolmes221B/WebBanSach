package com.MaxHolmes.WebBanSach.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.MaxHolmes.WebBanSach.Model.Category;

@Repository
public class CategoryDAO {
	public List<Category> findAll() {
		List<Category> categorys = new ArrayList<>();
		Connection connection = null;
		Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM category;");
			while (resultSet.next()) {
				int id = resultSet.getInt("id");
				String name = resultSet.getString("name");
				categorys.add(new Category(id, name));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categorys;
	}

	public Category findById(int id) {
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		Category category = new Category();
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement("SELECT * FROM category WHERE id = ?");
			ps.setInt(1, id);
			resultSet = ps.executeQuery();
			if(resultSet.next()) {
				String name = resultSet.getString("name");
				category = new Category(id, name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return category;
	}

	public int save(Category category, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		
		int result = 0;
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement(
					"INSERT INTO category (name) VALUES (?)");
			ps.setString(1, category.getName());
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}

	public int update(Category category, int id) {
		Connection connection = null;
		PreparedStatement ps = null;
		int result = 0;
		try {
			connection = DatabaseUtil.getConnection();
			ps = connection.prepareStatement(
					"UPDATE category SET name = ? WHERE id = ?");
			ps.setString(1, category.getName());
			ps.setInt(2, category.getId());
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
			ps = connection.prepareStatement("DELETE FROM category WHERE id = ?");
			ps.setInt(1, id);
			result = ps.executeUpdate();
		} catch (Exception e) {
			result = 0;
		}
		return result;
	}
}
