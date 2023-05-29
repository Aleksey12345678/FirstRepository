package com.mycompany.project1.service;

import com.mycompany.project1.domain.Role;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.UserDTO;
import com.mycompany.project1.mapper.UserMapper;
import com.mycompany.project1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private MailSender mailSender;

    private PasswordEncoder passwordEncoder;
    private UserMapper userMapper = new UserMapper(passwordEncoder);


    public UserServiceImpl(UserRepository userRepository, MailSender mailSender, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public boolean addUser(User user) {
        User userFromDB = findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        user.setActivationCode(UUID.randomUUID().toString());
        String pass = passwordEncoder.encode(user.getPassword());
        user.setPassword(pass);
        save(user);
        sendMessage(user);
        return true;
    }

    private void sendMessage(User user) {
        if (!StringUtils.isEmpty(user.getEmail())) {
            String message = String.format(
                    "Hello,%s\n" + "Welcome to messageprog," +
                            " Visit next link: http://localhost:8080/activate/%s",
                    user.getUsername()
                    , user.getActivationCode());
            mailSender.send(user.getEmail(), "Activation code", message);

        }
    }

    @Transactional
    public boolean activateUser(String code) {
        User user = userRepository.findByActivationCode(code);
        if (user == null) {
            return false;
        }
        user.setActivationCode(null);
        save(user);
        return true;
    }

    public List<UserDTO> findAll() {
        return userMapper.fromUserList(userRepository.findAll());
    }

    @Transactional
    public void save(User user, String username, Map<String, String> form) {

        user.setUsername(username);
        Set<String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        user.getRoles().clear();
        for (String key : form.keySet()) {
            if (roles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        save(user);
    }

    public void updateProfile(User user, String password, String email) {
        String userEmail = user.getEmail();
        boolean isEmailChanged = (email != null && !email.equals(userEmail)) ||
                userEmail != null && !userEmail.equals(email);
        if (isEmailChanged) {
            user.setEmail(email);
            if (!StringUtils.isEmpty(email)) {
                user.setActivationCode(UUID.randomUUID().toString());
            }
        }
        if (!StringUtils.isEmpty(password)) {
            user.setPassword(password);
        }
        save(user);
        if (isEmailChanged) {
            sendMessage(user);
        }
    }

    @Override
    public boolean save(UserDTO userDTO) {
        if (!Objects.equals(userDTO.getPassword(), userDTO.getMatchingPassword())) {
            throw new RuntimeException("Password is not equals");
        }

        save(userMapper.toUser(userDTO));
        return true;
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }


    public User findByUsername(String name) {
        return userRepository.findFirstByUsername(name);
    }

}

