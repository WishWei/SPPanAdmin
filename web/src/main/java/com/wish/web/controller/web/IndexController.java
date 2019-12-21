package com.wish.web.controller.web;

import java.util.List;

import com.wish.service.IUserService;
import com.wish.web.controller.BaseController;
import com.wish.domain.po.UserPO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController extends BaseController{
	
	@Autowired
	private IUserService userService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());

	@RequestMapping(value={"/","/index"})
	public String index(){
		List<UserPO> users = userService.findAll();
		logger.debug(users.toString());
		return "index";
	}
}
