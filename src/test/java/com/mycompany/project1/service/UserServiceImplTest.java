package com.mycompany.project1.service;

import com.mycompany.project1.domain.Role;
import com.mycompany.project1.domain.User;
import com.mycompany.project1.domain.dto.UserDTO;
import com.mycompany.project1.mapper.UserMapper;
import com.mycompany.project1.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;


import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    @Mock
    private MailSender mailSender;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findByUsername() {
        User testUser=new User();
        testUser.setUsername("asd");
        Mockito.doReturn(testUser).
                when(userRepository).findFirstByUsername("asd");
        User actualResult= (User) userService.loadUserByUsername("asd");
        Assertions.assertTrue(actualResult!=null);
        assertEquals("asd",actualResult.getUsername());

        Mockito.verify(userRepository, Mockito.times(1)).findFirstByUsername(Mockito.any());
    }
    @Test
    void addUser(){
        User user=new User();
        user.setEmail("someemail@mail.ru");
        boolean isUserCreated= userService.addUser(user);
        Assertions.assertTrue(isUserCreated);
        Assert.assertNotNull(user.getActivationCode());
        Assert.assertTrue(CoreMatchers.is(user.getRoles()).matches(Collections.singleton(Role.USER)));
        Mockito.verify(userRepository,Mockito.times(1)).save(user);
        Mockito.verify(mailSender, Mockito.times(1)).send(
                ArgumentMatchers.eq(user.getEmail()),
                ArgumentMatchers.eq("Activation code"),
                ArgumentMatchers.contains("Welcome to messageprog")
        );

    }
    @Test
    public void addUserFailTest(){
        User user =new User();
        user.setUsername("w");
        Mockito.doReturn(new User()).when(userRepository).findFirstByUsername("w");
        boolean isUserCreated=userService.addUser(user);
        assertFalse(isUserCreated);
        Mockito.verify(userRepository, Mockito.times(0)).save(ArgumentMatchers.any(User.class));
        Mockito.verify(mailSender, Mockito.times(0)).send(
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString(),
                ArgumentMatchers.anyString()
        );

    }

    @Test
    void activateUser() {
        User user=new User();
        user.setActivationCode("s");
        Mockito.doReturn(user).when(userRepository).findByActivationCode("s");
        boolean isUserActivate= userService.activateUser("s");
        Assert.assertTrue(isUserActivate);
        Assert.assertNull(user.getActivationCode());
        Mockito.verify(userRepository,Mockito.times(1)).findByActivationCode("s");
    }
    @Test
    public  void activateUserFailTest(){

        boolean isUserActivate= userService.activateUser("s");
        Assert.assertFalse(isUserActivate);
        Mockito.verify(userRepository, Mockito.times(1)).findByActivationCode("s");
    }

    @Test
    void findAll() {
        List<User> userList=new ArrayList<>();
       userList.add(new User());
       Mockito.doReturn(userList).when(userRepository).findAll();
       List<UserDTO> currentList=userService.findAll();
       Assert.assertTrue(!currentList.isEmpty());
       Mockito.verify(userRepository,Mockito.times(1)).findAll();

    }
}