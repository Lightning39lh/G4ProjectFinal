
package com.bootcamp.mvc.controllers.services;

import com.bootcamp.mvc.model.Login;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class LoginService {

    public Object loadUser(Login login) {
        
        String url = "http://localhost:8080/login";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, login, Login.class);
        return login;
    }

}
