package com.user.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.configuration.ContactInfo;
import com.user.entity.Customer;
import com.user.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Value("${welcome-message}")
    private String buildVersion;
	

    @Autowired
    private UserService userService;
    
    @Autowired
    private  ContactInfo contactInfo;


    @GetMapping("/users")
    public List<String> getAllUsers() {
        return userService.getAllUsers();
    }
    
    @GetMapping("/version")
    public String demo() {
    	return "version is"+buildVersion;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getUserById(@PathVariable Integer id) {
        Optional<Customer> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Customer createUser(@RequestBody Customer user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateUser(@PathVariable Integer id, @RequestBody Customer userDetails) {
        Customer updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/contact-info")
    public String getContactInfo() {
        return "Message: " + contactInfo.message() + ", Name: " + contactInfo.name();
    }
}
