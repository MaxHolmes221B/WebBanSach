package com.MaxHolmes.WebBanSach.Controller;

import com.MaxHolmes.WebBanSach.DAO.UserDAO;
import com.MaxHolmes.WebBanSach.Model.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller

public class UserController {
	@Autowired
    private UserDAO userDAO;


    @GetMapping("/users")
    public String getAllUsers(Model model) {
        List<User> users = userDAO.findAll();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("user/profile")
    public String getUserById(Model model, HttpSession session) {
    	if(session.getAttribute("user") == null) {
    		return "redirect:/signin";
    	}
		User user = (User) session.getAttribute("user");
		model.addAttribute("user", user);
		return "user_profile";
    }

    @PostMapping("/user/save/{id}")
    public String addUser(User user, @PathVariable String id) {
        int result = userDAO.save(user, Integer.valueOf(id));
        return "redirect:/books";
    }

    @PutMapping("/user/save/{id}")
    public String updateUser(User user, @PathVariable String id) {
        int result = userDAO.update(user, Integer.valueOf(id));
        return "redirect:/books";
    }

    @DeleteMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable String id) {
        int result = userDAO.deleteById(Integer.valueOf(id));
        return "redirect:/books";
    }
}
