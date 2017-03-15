/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.controller;

import com.harahap.ibrahim.domain.Users;
import com.harahap.ibrahim.repository.userRepositoryPaging;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aim MSI
 */
@Controller
public class userController 
{
    @Autowired
    private userRepositoryPaging userRepo;
    
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUser()
    {
        return "login/adduser";
    }
    
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String postUser(HttpServletRequest request)
    {
        //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Users user = new Users();
        user.setNama(request.getParameter("nama"));
        user.setCreatedAt(new Date());
        user.setCreatedby(request.getParameter("nama"));
        user.setUser_role("USER");
        user.setActive(Boolean.TRUE);
        user.setUsername(request.getParameter("username"));
        user.setPassword(passwordEncoder.encode(request.getParameter("password")));
        user.setPosisi(request.getParameter("posisi"));
        
        userRepo.save(user);
        return "redirect:login";
    }
}
