package com.MaxHolmes.WebBanSach.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.MaxHolmes.WebBanSach.DAO.UserDAO;
import com.MaxHolmes.WebBanSach.Model.User;

import jakarta.servlet.http.HttpSession;

@Controller
public class SignupController {
	@Autowired
	private UserDAO userDAO;
	
	@GetMapping("/signup")
	public String getSignup(Model model, HttpSession session) {
		User loggedUser = (User) session.getAttribute("user");
		if(loggedUser != null) {
			return "redirect:/trangchu";
		}
		User user = new User();
		model.addAttribute("user", user);
		return "signup";
	}
	
	@PostMapping("/signup")
	public String signup(Model model, HttpSession session, User user, RedirectAttributes redirect) {
		System.out.print(user);
		User loggedUser = (User) session.getAttribute("user");
		if(loggedUser != null) {
			return "redirect:/trangchu";
		}
		int success = 0;
		try {
			if(userDAO.findByUsername(user.getUsername()).size() == 0) {
				user.setRole("USER");
				success = userDAO.save(user, 0);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	    if(success == 0) {
	    	redirect.addFlashAttribute("thongBao", "Đăng ký không thành công! Tài khoản bị trùng!");
	        return "redirect:/signup";
	    } else {
	    	redirect.addFlashAttribute("thongBao", "Đăng ký thành công!");
	        return "redirect:/signin";
	    }   
	}
}
