/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.controller;

import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Aim MSI
 */
@Controller
public class loginController {

    @RequestMapping("/login")
    public String login(HttpServletResponse http) {
        Authentication authentication
                = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            return "login/formlogin";
        }
        if (authentication instanceof AnonymousAuthenticationToken) {
            return "login/formlogin";
        }
        return "redirect:/";
    }
}
