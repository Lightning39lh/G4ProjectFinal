
package com.bootcamp.mvc.controllers.services;

import com.bootcamp.mvc.model.MyUser;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserService {
    
    
    
    public ArrayList<MyUser> getAllUsers() {
        String url = "http://localhost:8080/MyUsers/";
        RestTemplate restTemplate = new RestTemplate();
        ArrayList<MyUser> users = new ArrayList<MyUser>();
        users = restTemplate.getForObject(url, ArrayList.class);
        return users;
    }
    /*

    public Person savePerson(Person person) {
        String url = "http://localhost:8080/api/person";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.postForObject(url, person, Person.class);
        return person;
    }

    public Person findById(Long id) {
        System.out.println("asd");
        String url = "http://localhost:8080/api/person/" + id;
        RestTemplate restTemplate = new RestTemplate();
        Person person = restTemplate.getForObject(url, Person.class);
        System.out.println(person);
        return person;
    }*/
}
