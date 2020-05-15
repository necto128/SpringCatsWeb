package com.example.demo.service;

import com.example.demo.enums.Role;
import com.example.demo.model.User;
import com.example.demo.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userRepository.findByUsername(s);
    }

    public User ckeckUser(User users) {
        return userRepository.findByUsername(users.getUsername());
    }

    public void saveUser(User users) {
        userRepository.save(users);
    }

    public Iterable<User> listUser() {
        Iterable<User> listUser = userRepository.findAll();
        return listUser;
    }

    public void editUsers(User users, MultiValueMap<String, String> form) {
        User user = userRepository.findById(users.getId()).orElseThrow();
        user.setUsername(users.getUsername());
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {

            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);
    }

    public String createUser(User users) {
        users.setActive(true);
        users.setRoles(Collections.singleton(Role.USER));
        userRepository.save(users);
        return null;
    }

    public Iterable<User> userlist() {
        Iterable<User> listcat = userRepository.findAll();
        return listcat;
    }
}
