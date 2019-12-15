package com.wish.web.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.wish.web.controller.BaseController;

@Controller
public class AdminIndexController extends BaseController{
	@RequestMapping(value ={"/admin/","/admin/index"})
	public String index(){
		
		return "admin/index";
	}
	
	@RequestMapping(value = {"/admin/welcome"})
	public String welcome(){
		
		return "admin/welcome";
	}
}
