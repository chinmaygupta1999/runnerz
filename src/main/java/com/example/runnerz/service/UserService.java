package com.example.runnerz.service;

import com.example.runnerz.model.UserRequest;
import com.example.runnerz.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    

    public User createUser(UserRequest request) {
        User user = User.builder().name(request.getName()).id(1).build();

        return user;
    }

}
