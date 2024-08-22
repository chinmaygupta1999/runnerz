package com.example.runnerz;

import com.example.runnerz.client.UserHttpClient;
import com.example.runnerz.user.User;
import com.example.runnerz.client.UserRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class Application {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    UserHttpClient userHttpClient(){
        RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/users/");
        HttpServiceProxyFactory httpServiceProxyFactory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
        return httpServiceProxyFactory.createClient(UserHttpClient.class);
    }

    @Bean
    CommandLineRunner runner(UserHttpClient userHttpClient) {

        return args -> {
            User user = userHttpClient.findById(1);
            System.out.println(user.getName());
//            User triv = User.builder().name().build();
        };
    }

}
