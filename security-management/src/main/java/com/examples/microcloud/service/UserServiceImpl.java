package com.examples.microcloud.service;

import com.examples.microcloud.model.dto.AddUserRequest;
import com.examples.microcloud.model.entity.User;
import com.examples.microcloud.repo.UserRepository;
import com.examples.microcloud.service.api.UserService;
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
