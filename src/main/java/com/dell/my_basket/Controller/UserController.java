package com.dell.my_basket.Controller;

import com.dell.my_basket.JwtAuth.JwtUtil;
import com.dell.my_basket.JwtAuth.MyUserDetailsService;
import com.dell.my_basket.JwtAuth.Payload.Request.AuthenticationRequest;
import com.dell.my_basket.JwtAuth.Payload.Request.SignUpRequest;
import com.dell.my_basket.JwtAuth.Payload.Response.AuthenticationResponse;
import com.dell.my_basket.JwtAuth.UserDetail;
import com.dell.my_basket.Models.User;
import com.dell.my_basket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequestMapping("/api/user")
@RestController
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    UserRepository userRepository;

    @RequestMapping({"/user"})
    public String hello(){
        return "Izzath";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody AuthenticationRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetail userDetails = (UserDetail) userDetailsService
                .loadUserByUsername(loginRequest.getUsername());

      //  UserDetail userDetailss = (UserDetail) authentication.getPrincipal();
        final String jwt=jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt,
                "Bearer",
                userDetails.getUsername(),
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    //@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Error: Email is already taken!");
        }
        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setName(signUpRequest.getName());
        user.setNumber(signUpRequest.getNumber());
        user.setPassword(signUpRequest.getPassword());
        userRepository.save(user);

        return ResponseEntity.ok("User Registered successfully!");
    }

}
