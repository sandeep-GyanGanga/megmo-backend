package com.megmo.megmoProject.controller;


import java.security.Principal;

import com.megmo.megmoProject.config.JwtUtils;
import com.megmo.megmoProject.entities.JwtRequest;
import com.megmo.megmoProject.entities.JwtResponse;
import com.megmo.megmoProject.entities.User;
import com.megmo.megmoProject.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;







import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;





@RestController
@CrossOrigin("*")
public class AuthenticateController {

    /**/

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtils;


    //generate token

    @PostMapping("/generate-token")
    public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception {

        try {

            authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());


        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new Exception("User not found ");


        }

        /////////////authenticate

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));


    }


    private void authenticate(String username, String password) throws Exception {

        try {

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

        } catch (DisabledException e) {
            throw new Exception("USER DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }
    }



//    iss api mein header mein jo  token aayega uss token se user ka username nikalkar , db se user ki details nikalkar return kar degi.

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal) {

        return (User) this.userDetailsService.loadUserByUsername(principal.getName());

    }



}
