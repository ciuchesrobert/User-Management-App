package com.supportportal.service.impl;

import com.supportportal.domain.UserPrincipal;
import com.supportportal.repository.UserRepository;
import com.supportportal.domain.User;
import com.supportportal.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private Logger LOGGER = LoggerFactory.getLogger(getClass());
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("User not found bu username " + username);
            throw new UsernameNotFoundException("User not found bu username " + username);
        } else {
            user.setLastLoginDate(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);
            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by username " + username);
            return userPrincipal;
        }
    }
}
