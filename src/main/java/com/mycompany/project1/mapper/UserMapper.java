package com.mycompany.project1.mapper;

import com.mycompany.project1.domain.Product;
import com.mycompany.project1.domain.Role;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.UserDTO;
import com.mycompany.project1.domain.dto.products.BookDTO;
import com.mycompany.project1.domain.products.Book;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
public class UserMapper {
    private PasswordEncoder passwordEncoder;

    public UserDTO fromUser(User user) {
        return new UserDTO(user.getId(), user.getUsername(), user.getEmail(), user.getRoles());
    }

    public List<UserDTO> fromUserList(List<User> userList) {
        return userList.stream().map(user -> fromUser(user)).collect(Collectors.toList());
    }

    public User toUser(UserDTO userDTO) {
        User user = User.builder()
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .email(userDTO.getEmail())
                .roles(Collections.singleton(Role.USER))
                .build();

        return user;
    }
}
