package com.user.service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.dao.UserRepository;
import com.user.entity.Customer;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<String> getAllUsers() {
        List<String> users = new ArrayList<>();

    
    	    users.add("User1");
    	    users.add("User2");
    	    users.add("User3");
    	    return users;
    	
    }

    public Optional<Customer> getUserById(Integer id) {
        return userRepository.findById(id);
    }

    public Customer createUser(Customer user) {
        return userRepository.save(user);
    }

    public Customer updateUser(Integer id, Customer userDetails) {
        return userRepository.findById(id).map(user -> {
            user.setName(userDetails.getName());
            user.setEmail(userDetails.getEmail());
            return userRepository.save(user);
        }).orElseGet(() -> {
//            userDetails.setId(id);
            return userRepository.save(userDetails);
        });
    }

    public void deleteUser(Integer id) {
        userRepository.deleteById(id);
    }
}
