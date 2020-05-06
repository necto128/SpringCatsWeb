package com.example.demo.service;

import com.example.demo.model.Users;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public Users ckeckUser(Users users) {
        return userRepository.findByUsername(users.getUsername());
    }

    public void saveUser(Users users) {
        userRepository.save(users);
    }

    public List<Users> listUser() {
        return userRepository.findAll();
    }
}
