/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.harahap.ibrahim.repository;

import com.harahap.ibrahim.domain.Users;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Aim MSI
 */
@Component
public class userRepositoryFindByUsernameImpl implements userRepositoryFindByUsername
{
    @Autowired
    private EntityManager em;
    
    @Override
    public Users findByUsername(String username) {
        return em.createQuery(
                "from Users where username = :username", Users.class
        ).setParameter("username", username).getSingleResult();
    }
}
