package com.example.microcloud;

import com.example.microcloud.model.dto.AddUserRequest;
import com.example.microcloud.model.entity.User;
import com.example.microcloud.repo.UserRepository;
import com.example.microcloud.service.api.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class UserRegistryTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void test_fetchAll() {
        List<User> userList = userRepository.findAll();
        Assert.notEmpty(userList,"hgghjg ygyu");
    }

    @Test
    void test_addUserService() {
        AddUserRequest addUserRequest = new AddUserRequest();
        addUserRequest.setNickname("hadi_tayebi");
        addUserRequest.setUsername("hadi_ta");
        addUserRequest.setPassword("123");
        addUserRequest.setConfirmPassword("123");
        userService.addUser(addUserRequest);
    }
}
