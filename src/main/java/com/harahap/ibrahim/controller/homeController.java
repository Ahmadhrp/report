/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.controller;

import com.harahap.ibrahim.repository.userRepositoryPaging;
import java.util.Collection;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Aim MSI
 */
@Controller
public class homeController {

    @Autowired
    private userRepositoryPaging userRepo;

    @RequestMapping("/")
    public String home(Model model, HttpServletRequest request) {
        //System.out.println(userRepo.findAll());       
        //userRepo.save(user);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) auth.getAuthorities();
        if (isRolePresent(authorities, "USER")) {
            //System.out.println("User");
            return "user/dashboard";
        } else {
            //System.out.println("Admin");
            model.addAttribute("users", userRepo.findAll());
            return "admin/home";
        }
//            if (request.isUserInRole("USER")) {
//                System.out.println("User");
//                return "user/dashboard";
//            } else {
//                System.out.println("Admin");
//                model.addAttribute("users", userRepo.findAll());
//                return "admin/home";
//            }

    }

    private boolean isRolePresent(Collection<GrantedAuthority> authorities, String role) {
        boolean isRolePresent = false;
        for (GrantedAuthority grantedAuthority : authorities) {
            isRolePresent = grantedAuthority.getAuthority().equals(role);
            if (isRolePresent) {
                break;
            }
        }
        return isRolePresent;
    }
}
