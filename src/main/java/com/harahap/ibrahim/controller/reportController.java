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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Aim MSI
 */
@Controller
public class reportController {

    @Autowired
    private userRepositoryFindByUsernameImpl userRepoFindLoggedInUser;
    
    @Autowired
    private reportRepository reportRepo;

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String inputReport(Model model) {
        model.addAttribute("report", reportRepo.findAll());
        return "user/formreport";
    }

    @RequestMapping(value = "/daily", method = RequestMethod.POST)
    public String postReport(HttpServletRequest request, Principal principal) throws ParseException {

        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
        Users logged_in_user =  userRepoFindLoggedInUser.findByUsername(user.getUsername()); 
        Date date = dateformat.parse(request.getParameter("tanggal"));
        java.sql.Date sql = new java.sql.Date(date.getTime());
        //System.out.println(logged_in_user.getId());
        //You can invoke the getter for id on  user object.System.out.println(auth.);
//        System.out.println(dateformat.format(date));
        Dailyreport report = new Dailyreport();
        report.setUser_id(logged_in_user.getId());
        report.setTanggal(sql);
        report.setUraian(request.getParameter("uraian"));
        report.setCreatedAt(new Date());
        report.setCreatedby(principal.getName());

        reportRepo.save(report);
        return "redirect:daily";
    }
}
