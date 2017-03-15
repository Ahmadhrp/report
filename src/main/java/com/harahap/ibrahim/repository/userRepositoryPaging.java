/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.repository;

import com.harahap.ibrahim.domain.Users;
import javax.persistence.EntityManager;
import static org.apache.tomcat.jni.User.username;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.security.core.userdetails.User;

/**
 *
 * @author Aim MSI
 */
public interface userRepositoryPaging extends PagingAndSortingRepository<Users,Integer> {
    
    
}
