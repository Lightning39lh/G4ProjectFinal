package com.bootcamp.G4.controllers;

import com.bootcamp.G4.config.JWTTokenUtil;
import com.bootcamp.G4.model.MyUser;
import com.bootcamp.G4.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {
    
    @Autowired
    private UserService uS;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private JWTTokenUtil jTU;

    @PostMapping()
    public ResponseEntity<String> createToken(@RequestBody MyUser user)
    {
        authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        UserDetails uD = uS.loadUserByUsername(user.getUsername());
        String token =jTU.generateToken(uD);
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        return ResponseEntity.status(200).body(token);
    }

    /*
    @RequestMapping(value = "/generate-token", method = RequestMethod.POST)
    public ResponseEntity<AuthToken> register(@RequestBody MyUser loginUser) throws AuthenticationException {

        AuthenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword()));
        final UserDetails user = us.loadUserByUsername(loginUser.getUsername());
        final String token = JWTTokenUtil.generateToken(user);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthToken(token, user.getUsername()));
    }
    */
}
