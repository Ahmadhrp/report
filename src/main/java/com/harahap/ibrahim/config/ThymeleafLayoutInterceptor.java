/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.config;

import com.harahap.ibrahim.domain.Users;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author Aim MSI
 */
public class ThymeleafLayoutInterceptor extends HandlerInterceptorAdapter {

//    private static final String DEFAULT_LAYOUT_USER = "user/layout";
//    private static final String DEFAULT_LAYOUT_ADMIN = "admin/layout";
    private static final String DEFAULT_LAYOUT = "layout/layout";
    private static final String DEFAULT_VIEW_ATTRIBUTE_NAME = "view";

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView == null || !modelAndView.hasView()) {
            return;
        }
        String originalViewName = modelAndView.getViewName();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        Users user = (Users) authentication.getPrincipal();
//
//        if (user.getUser_role() == "USER") { 
//            modelAndView.setViewName(DEFAULT_LAYOUT_USER);
//        }
//        else{
//            modelAndView.setViewName(DEFAULT_LAYOUT_ADMIN);
//        }
        modelAndView.setViewName(DEFAULT_LAYOUT);
        modelAndView.addObject(DEFAULT_VIEW_ATTRIBUTE_NAME, originalViewName);
    }
}
