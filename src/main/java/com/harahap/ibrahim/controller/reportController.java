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
import static java.util.Arrays.sort;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Aim MSI
 */
@Controller
@RequestMapping("/report")
public class reportController {

    @Autowired
    private userRepositoryFindByUsernameImpl userRepoFindLoggedInUser;

    @Autowired
    private reportRepository reportRepo;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String listReport(Model model) {
        Sort sort = new Sort(Sort.Direction.ASC, "tanggal");
        model.addAttribute("reportlist", reportRepo.findAll(sort));
        return "user/reportlist";
    }

    @RequestMapping(value = "/daily", method = RequestMethod.GET)
    public String inputReport(@RequestParam(value = "id", required = false) Integer id, Model model, Dailyreport report) {

        if (id != null) {
            if (reportRepo.findOne(id) != null) {
                report = reportRepo.findOne(id);
                model.addAttribute("report", report);
                model.addAttribute("edit", true);
                return "user/form";
            }
        }
        model.addAttribute("report", new Dailyreport());
        return "user/form";
    }

    @RequestMapping("/hapus")
    public String hapusReport(@RequestParam("id") Integer id) {
        reportRepo.delete(id);
        return "redirect:/report/list";
    }

    @RequestMapping(value = "/daily", method = RequestMethod.POST)
    public String postReport(@RequestParam(value = "id", required = false) Integer id, HttpServletRequest request, Principal principal) throws ParseException {
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateformat.parse(request.getParameter("tanggal"));

        if (id != null) {
            Dailyreport report = reportRepo.findOne(id);
            report.setTanggal(date);
            report.setUraian(request.getParameter("uraian"));
            reportRepo.save(report);
            return "redirect:/report/list";
        }

        UserDetails user = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users logged_in_user = userRepoFindLoggedInUser.findByUsername(user.getUsername());
        Dailyreport report = new Dailyreport();
        report.setUser_id(logged_in_user.getId());
        report.setTanggal(date);
        report.setUraian(request.getParameter("uraian"));
        report.setCreatedAt(new Date());
        report.setCreatedby(principal.getName());

        reportRepo.save(report);
        return "redirect:/report/list";
    }
}
