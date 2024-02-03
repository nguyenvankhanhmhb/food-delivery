package com.cry.web_delivery.Controller;

import com.cry.web_delivery.Service.Imp.LoginServiceImp;
import com.cry.web_delivery.payload.ResponseData;
import com.cry.web_delivery.payload.request.SignupRequest;
import com.cry.web_delivery.util.JwtHelper;
import com.google.gson.Gson;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.crypto.SecretKey;
import java.util.List;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginServiceImp loginServiceImp;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtHelper jwtHelper;


    private Gson gson = new Gson();

    @PostMapping("/signin")
    public ResponseEntity<?>  signin(@RequestParam String username, @RequestParam String password){
        UsernamePasswordAuthenticationToken authen = new UsernamePasswordAuthenticationToken(username,password);
        authenticationManager.authenticate(authen);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<SimpleGrantedAuthority> roles = (List<SimpleGrantedAuthority>) authentication.getAuthorities();

        String jsonRole = gson.toJson(roles);

        String token = jwtHelper.generaToken(jsonRole);

        ResponseData responData = new ResponseData();
        responData.setStatus(200);
        responData.setData(token);
        responData.setDesc("");

        return new ResponseEntity<>(responData, HttpStatus.OK);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> singup(@RequestBody SignupRequest signupRequest){
        ResponseData responData = new ResponseData();
        responData.setData(loginServiceImp.addUser(signupRequest));
        return new ResponseEntity<>(responData, HttpStatus.OK);

    }


}
