package com.example.runnerz.client;

import com.example.runnerz.user.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
public class UserRestClient {
    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/users/")
                .build();
    }

    public List<User> findAll() {
        return restClient.get()
                .uri("")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findById(Integer id) {
        return restClient.get()
                .uri("/{id}", id)
                .retrieve()
                .body(User.class);
    }


}
