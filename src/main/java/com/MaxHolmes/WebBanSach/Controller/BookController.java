package com.MaxHolmes.WebBanSach.Controller;

import com.MaxHolmes.WebBanSach.DAO.BookDAO;
import com.MaxHolmes.WebBanSach.DAO.CategoryDAO;
import com.MaxHolmes.WebBanSach.Model.Book;
import com.MaxHolmes.WebBanSach.Model.Category;
import com.MaxHolmes.WebBanSach.Model.User;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Controller
public class BookController {
	@Autowired
    private BookDAO bookDAO;
	@Autowired
	private CategoryDAO categoryDAO;
	
    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<Book> books = bookDAO.findAll();
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("books", books);
        model.addAttribute("categories", categories);
        return "books";
    }

    @GetMapping("/book/{id}")
    public String getBookById(Model model, @PathVariable String id, HttpSession session) {
        Book book = bookDAO.findById(Integer.valueOf(id));
        List<Category> categories = categoryDAO.findAll();
        model.addAttribute("book", book);
        model.addAttribute("categories", categories);
        return "test";
    }

    @PostMapping("/book/save/{id}")
    public String addBook(Model model, Book book, @PathVariable String id,
    		HttpSession session,
    		@RequestParam("file") MultipartFile img,
    		RedirectAttributes redirect) throws IOException {
    	try {
    		User user = (User) session.getAttribute("user");
    		if(user != null)
    			if(user.getRole().equals("ADMIN")) {
    				System.out.println(user.getRole());
    				if (bookDAO.findByTitleAndAuthor(book.getTitle(), book.getAuthor()).size() > 0) {
    					redirect.addFlashAttribute("thongBaoLoi", "Sách bạn thêm đang bị trùng tiêu đề và tác giả với sách ở trong thư viện");
    					return "redirect:/book/" + "-1";
    				}
    	        	if (!img.isEmpty() && img != null) {
    	    						String originalFilename = img.getOriginalFilename();
    	    						String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
    	    						String newFilename = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + extension;
    	    						Path path = Paths.get("src/main/resources/static/images/");
    	    			            InputStream inputStream = img.getInputStream();
    	    			            Files.copy(inputStream, path.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
    	    			            inputStream.close();
    	    			            book.setPathImg("/images/" + newFilename);        
    	    	    }
    	        	int result = bookDAO.save(book, Integer.valueOf(id));
		            if(result == 0)
	    	        	return "redirect:/error";
    			}
    	}catch (Exception e) {
    		return "redirect:/error";
    	}
        return "redirect:/books";
    }

    @PutMapping("/book/save/{id}")
    public String updateBook(Book book, @PathVariable String id,
    		HttpSession session, 
    		@RequestParam("file") MultipartFile img,
    		RedirectAttributes redirect) throws IOException{
    	try {
    		User user = (User) session.getAttribute("user");
    		if(user != null)
    			if(user.getRole().equals("ADMIN")) {
    				if (bookDAO.findByTitleAndAuthor(book.getTitle(), book.getAuthor()).size() > 0 && book.getId() != bookDAO.findByTitleAndAuthor(book.getTitle(), book.getAuthor()).get(0).getId()) {
    					redirect.addFlashAttribute("thongBaoLoi", "Thông tin sách bạn sửa đang bị trùng tiêu đề và tác giả với sách ở trong thư viện");
    					return "redirect:/book/" + id;
    				}
	        	if (!img.isEmpty() && img != null) {
	        					System.out.println("Anh duoc up load");
	    						String originalFilename = img.getOriginalFilename();
	    						String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
	    						String newFilename = System.currentTimeMillis() + "_" + UUID.randomUUID().toString() + extension;
	    						Path path = Paths.get("src/main/resources/static/images/");
	    			            InputStream inputStream = img.getInputStream();
	    			            Files.copy(inputStream, path.resolve(newFilename), StandardCopyOption.REPLACE_EXISTING);
	    			            inputStream.close();
	    			            book.setPathImg("/images/" + newFilename);
	    			            
	        	}
				int result = bookDAO.update(book, Integer.valueOf(id));
    	        if(result == 0)
    	        	return "redirect:/error";
    			}
    	}
    	catch (Exception e) {
    		return "redirect:/error";
    	}
        return "redirect:/books";
    }

    @DeleteMapping("/book/delete/{id}")
    public String deleteBook(@PathVariable String id, HttpSession session) {
    	User user = (User)session.getAttribute("user");
    	if(user != null) {
    		System.out.print(user);
    		if(user.getRole().equals("ADMIN")) {
    			System.out.print(1);
    			int result = bookDAO.deleteById(Integer.valueOf(id));
    			if(result != 0) {
    				return "redirect:/books";
    			}
    			return "redirect:/error";
    		}
    	}
    	return "redirect:/books";
    }
}
