package com.wish.app.controller;

import com.wish.domain.po.UserPO;
import com.wish.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: SPPanAdmin
 * @description:
 * @author: wish
 * @create: 2020-01-01 12:24
 **/
@RestController
public class TestController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = { "/list" })
    public Page<UserPO> list(
            @RequestParam(value="searchText",required=false) String searchText) {

        return null;
    }
}
