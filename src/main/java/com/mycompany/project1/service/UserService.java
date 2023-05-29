package com.mycompany.project1.service;

import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    boolean save(UserDTO userDTO);
    void save(User user);
    public User findByUsername(String name);
//    List<UserDTO> getAll();
//
//    User findByName(String name);
//    void updateProfile(UserDTO userDTO);
}
