package com.example.microcloud.service;

import com.example.microcloud.model.dto.AddUserRequest;
import com.example.microcloud.model.entity.User;
import com.example.microcloud.repo.UserRepository;
import com.example.microcloud.service.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    protected UserRepository userRepository;

    @Transactional
    @Override
    public void addUser(AddUserRequest addUserRequest) {
        User user = addUserRequest.toUser();
        user.setDisabled(Boolean.FALSE);
        user.setLocked(Boolean.FALSE);
        userRepository.save(user);
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
