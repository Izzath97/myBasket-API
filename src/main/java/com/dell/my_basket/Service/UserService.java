package com.dell.my_basket.Service;

import com.dell.my_basket.Models.User;
import com.dell.my_basket.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public ResponseEntity<?> getAllUsers(){
        List<User> userList=new ArrayList<>();
        userRepository.findAll().forEach(user -> userList.add(user));
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }
}
