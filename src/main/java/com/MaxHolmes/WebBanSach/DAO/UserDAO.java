package com.MaxHolmes.WebBanSach.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.MaxHolmes.WebBanSach.Model.Book;
import com.MaxHolmes.WebBanSach.Model.User;

@Repository
public class UserDAO implements DAO<User> {
    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseUtil.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM user;");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String pnb = resultSet.getString("pnb");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                users.add(new User(id, name, username, password, dob, address, pnb, email, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = new User();
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM user WHERE id = ?");
            ps.setInt(1, id);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String pnb = resultSet.getString("pnb");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                user = new User(id, name, username, password, dob, address, pnb, email, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public int save(User user, int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement(
                    "INSERT INTO user(name, username, password, dob, address, pnb, email, role) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getDob());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getPnb());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getRole());
            result = ps.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }

    public int update(User user, int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement(
                    "UPDATE user SET name = ?, username = ?, password = ?, dob = ?, address = ?, pnb = ?, email = ?, role = ? WHERE id = ?");
            ps.setString(1, user.getName());
            ps.setString(2, user.getUsername());
            ps.setString(3, user.getPassword());
            ps.setDate(4, user.getDob());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getPnb());
            ps.setString(7, user.getEmail());
            ps.setString(8, user.getRole());
            ps.setInt(9, user.getId());
            result = ps.executeUpdate();
            result = 1;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }

    public int deleteById(int id) {
        Connection connection = null;
        PreparedStatement ps = null;
        int result = 0;
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement("DELETE FROM user WHERE id = ?");
            ps.setInt(1, id);
            result = ps.executeUpdate();
        } catch (Exception e) {
            result = 0;
        }
        return result;
    }
    public List<User> findByUsername(String username) {
    	Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<User> users = new ArrayList<>();
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM user WHERE username = ?");
            ps.setString(1, username);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String password = resultSet.getString("password");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String pnb = resultSet.getString("pnb");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                users.add(new User(id, name, username, password, dob, address, pnb, email, role));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }
    
    public User findByUsernameAndPassword(String username, String password) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        User user = null;
        try {
            connection = DatabaseUtil.getConnection();
            ps = connection.prepareStatement("SELECT * FROM user WHERE username = ? AND password = ?");
            ps.setString(1, username);
            ps.setString(2, password);
            resultSet = ps.executeQuery();
            if (resultSet.next()) {
            	int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Date dob = resultSet.getDate("dob");
                String address = resultSet.getString("address");
                String pnb = resultSet.getString("pnb");
                String email = resultSet.getString("email");
                String role = resultSet.getString("role");
                user = new User(id, name, username, password, dob, address, pnb, email, role);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
