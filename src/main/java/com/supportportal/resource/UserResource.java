package com.supportportal.resource;

import com.supportportal.domain.User;
import com.supportportal.exceptions.ExceptionHandling;
import com.supportportal.exceptions.domain.UsernameExistsException;
import com.supportportal.exceptions.domain.UsernameNotFountException;
import com.supportportal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping(path = {"/","/user"})
public class UserResource extends ExceptionHandling {

    private UserService userService;

    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws UsernameNotFountException, UsernameExistsException {
        User newUser = userService.register(user.getFirstName(), user.getLastName(), user.getUsername(), user.getEmail());
        return new ResponseEntity<>(newUser, OK);
    }
}
