package com.MaxHolmes.WebBanSach.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MaxHolmes.WebBanSach.DAO.UserDAO;
import com.MaxHolmes.WebBanSach.Model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class SigninController {
	@Autowired
	private UserDAO userDAO;
	@GetMapping("/signin")
	public String getSignIn(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("user");
		if(loggedUser != null) {
			return "redirect:/books";
		}
		return "signin";
	}
	
	@PostMapping("/signin")
	public String postLogin(Model model, @RequestParam("username") String username,
	                        @RequestParam("password") String password,
	                        HttpSession session, RedirectAttributes redirect) {
		User loggedUser = (User) session.getAttribute("user");
		if(loggedUser != null) {
			return "redirect:/books";
		}
		
	    // Kiểm tra thông tin đăng nhập
		User user = userDAO.findByUsernameAndPassword(username, password);
	    if (user != null) {
	        // Lưu người dùng vào session
	    	session.setAttribute("user", user);
	        return "redirect:/books";
	    } else {
	    	redirect.addFlashAttribute("thongBao", "Sai tài khoản hoặc mật khẩu!");
	        return "redirect:/signin";
	    }
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/books";
	}
}