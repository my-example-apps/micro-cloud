package com.examples.microcloud.api;

import com.examples.microcloud.model.dto.AddUserRequest;
import com.examples.microcloud.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<String> add(@Validated @RequestBody AddUserRequest addUserRequest) {
        userService.addUser(addUserRequest);
        return ResponseEntity.ok("Ok");
    }
}
