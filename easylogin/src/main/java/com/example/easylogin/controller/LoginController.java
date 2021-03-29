package com.example.easylogin.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.example.easylogin.model.dao.UserRepository;
import com.example.easylogin.model.entity.User;






@Controller
public class LoginController {
	
	@Autowired
	UserRepository userRepos;
	
	
	
	//トップページを担当するメソッド
	@RequestMapping("/")
	public String index() {
		return "index";
	}
	
	
	//ログイン後おページ遷移を担当するメソッド
	@RequestMapping("/login")
	public String login(
			@RequestParam("userName") String userName,
			@RequestParam("password") String password,
			Model m) {
		
		String message="welcome! ";
		
		List<User> users =userRepos.findByUserNameAndPassword(userName, password);
		
		if( users.size() > 0 ) {
			User user =users.get(0);
			message += user.getFullName();
		} else {
			message +="guest";
		}
		
		m.addAttribute("message",message);
		return "login";
	}
}
