package com.TDD.ABnB.controller;

import com.TDD.ABnB.models.AppUser;
import com.TDD.ABnB.models.jwt.JwtRequest;
import com.TDD.ABnB.models.jwt.JwtResponse;
import com.TDD.ABnB.services.JwtUserDetailsService;
import com.TDD.ABnB.services.app_user_service.AppUserService;
import com.TDD.ABnB.utilities.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@CrossOrigin("*")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private AppUserService appUserServiceImpl;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtUserDetailsService userDetailsService;

    @PostMapping(path = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        System.out.println("token authenticating");
        return ResponseEntity.ok(new JwtResponse(token));
    }
    @PostMapping(path = "/token-authenticate")
    public ResponseEntity<?> tokenAuthenticate(HttpServletRequest request) throws Exception {
        final String requestTokenHeader = request.getHeader("Authorization");
        System.out.println("HEADER "+ requestTokenHeader);
        String jwtToken = requestTokenHeader.substring(7);
        String username=jwtTokenUtil.getUsernameFromToken(jwtToken);
        AppUser appUser = appUserServiceImpl.findFirstByName(username);
        appUser.setPassword(null);
        System.out.println("token authenticating");
        return new ResponseEntity<AppUser>(appUser,HttpStatus.ACCEPTED);
    }


//    @PostMapping(path = "/check-token")
//    public ResponseEntity<Boolean> checkToken(HttpServletRequest request, @RequestBody JwtRequest authenticationRequest) throws Exception {
//        final String requestTokenHeader = request.getHeader("Authorization");
//        String jwtToken = requestTokenHeader.substring(7);
//        ResponseEntity<Boolean> res= new ResponseEntity<Boolean>(jwtTokenUtil.isTokenExpired(jwtToken), HttpStatus.ACCEPTED);
//        return res;
//    }


    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID USERNAME OR PASSWORD", e);
        }
    }
}