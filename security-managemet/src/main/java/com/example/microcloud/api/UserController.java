package com.example.microcloud.api;

import com.example.microcloud.model.dto.AddUserRequest;
import com.example.microcloud.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {


    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> add(@Validated @RequestBody AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
        return ResponseEntity.ok("Ok");
    }
}
