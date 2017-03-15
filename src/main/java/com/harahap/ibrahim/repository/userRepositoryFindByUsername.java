/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.repository;

import com.harahap.ibrahim.domain.Users;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UserDetails;

/**
 *
 * @author Aim MSI
 */
public interface userRepositoryFindByUsername extends Repository<Users,Integer>  
{
    public Users findByUsername(String username);
    
}
