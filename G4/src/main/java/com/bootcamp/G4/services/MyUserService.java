
package com.bootcamp.G4.services;

import com.bootcamp.G4.model.MyUser;
import com.bootcamp.G4.repositories.MyUserRepository;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {
    
    @Autowired
    MyUserRepository uR;

    public ArrayList<MyUser> getAllUsers() {
        return (ArrayList<MyUser>) uR.findAll();
    }

    public MyUser saveUser(MyUser user) {
        return uR.save(user);
    }

    public Optional<MyUser> getUserByID(Long id) {
        return uR.findById(id);
    }
    //GET BY USER
    public Optional<MyUser> findByUserName(String userName) {
        return uR.findByUsername(userName);
    }
    //DELETE
    public boolean deleteById(Long id) {
        try {
            uR.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //EDIT
    public boolean editByUserName(MyUser user) {
        if (uR.findById(user.getId()) != null){
            uR.save(user);
            return true;
        } else
            return false;        
    }
    
    
}
