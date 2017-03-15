/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.repository;

import com.harahap.ibrahim.domain.Dailyreport;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author Bensolo
 */
public interface reportRepository extends PagingAndSortingRepository<Dailyreport, Integer>{
    
}
