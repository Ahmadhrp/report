/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.controller;

import com.harahap.ibrahim.domain.Dailyreport;
import com.harahap.ibrahim.domain.Users;
import com.harahap.ibrahim.repository.reportRepository;
import com.harahap.ibrahim.repository.userRepositoryFindByUsernameImpl;
import java.security.Principal;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aim MSI
 */
@Controller
public class reportController {

    @Autowired
    private userRepositoryFindByUsernameImpl userRepo;
            
    @Autowired
    private reportRepository reportRepo;

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String inputReport() {
        return "user/formreport";
    }

    @RequestMapping(value = "/daily", method = RequestMethod.POST)
    public String postReport(HttpServletRequest request, Principal principal) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        //auth.getPrincipal().
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        //userRepo.findByUsername(user.getUsername());           
        Users logged_in_user =  userRepo.findByUsername(user.getUsername());
        
        System.out.println(logged_in_user.getId());
        //You can invoke the getter for id on  user object.System.out.println(auth.);
        //System.out.println(user.getUsername().);
        Dailyreport report = new Dailyreport();
        //report.setUser_id(user.getId());
        report.setTanggal(new Date());
        report.setUraian(request.getParameter("uraian"));
        report.setCreatedAt(new Date());
        report.setCreatedby(principal.getName());

        //reportRepo.save(report);
        return "redirect:daily";
    }
}
