
package com.bootcamp.G4.services;

import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyTokenService {
    @Autowired
    MyTokenRepository tR;

    public ArrayList<MyToken> getAllTokens()) {
        return (ArrayList<MyToken>) tR.findAll();
    }
    
    public Optional<MyToken> getTokenByName(String name)) {
        return (Optional<MyToken>) tR.findByName(name);
    }
    
    public MyToken getTokenByID(Long id) {
        return tR.findById(id).get();
    }
     public boolean removeToken(Long id) {
        try {
            tR.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    //EDIT
     public boolean editByID(MyToken token) {
        if (tR.findById(token.getId())){
            tR.save(token);
            return true;
        } else
            return false;        
    }
    
     
}
