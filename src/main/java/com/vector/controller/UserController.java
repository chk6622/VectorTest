package com.vector.controller;

import com.vector.Repository.IUserRepository;
import com.vector.apiresponse.*;
import com.vector.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/***
 * A service for creating, retrieving, updating, and deleting users.
 */
@RestController()
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    /***
     * Creates a new user
     * @param userDto
     * @return
     */
    @PostMapping("/user")
    public Object create(@RequestBody @Validated User userDto){
        System.out.println("test");
        com.vector.model.User result = this.userRepository.findByEmail(userDto.getEmail());
        if(result == null){
            this.userRepository.save(userDto.createUserModel());
            return new ResponseEntity(HttpStatus.CREATED);
        }
        return new ResponseEntity<MyError>(new MyError(HttpStatus.CONFLICT+"","User exists."),HttpStatus.CONFLICT);
    }

    /***
     * Retrieve an existing user.
     * @param email user's email
     * @return
     */
    @GetMapping("/user/{email}")
    public Object retrieve(@PathVariable String email){
        com.vector.model.User user = this.userRepository.findByEmail(email);
        if(user!=null){
            return new ResponseEntity<User>(user.createUserDto(),HttpStatus.OK);
        }
        return new ResponseEntity<MyError>(new MyError(HttpStatus.NOT_FOUND+"","Not found the user."),HttpStatus.NOT_FOUND);

    }

    /***
     * Update an existing user.
     * @param userDto new user data object
     * @return
     */
    @PutMapping("/user")
    public Object update(@RequestBody @Validated User userDto){
        com.vector.model.User result = this.userRepository.findByEmail(userDto.getEmail());
        if(result != null){
            this.userRepository.save(userDto.createUserModel());
            return new ResponseEntity(HttpStatus.NO_CONTENT );

        }
        return new ResponseEntity<MyError>(new MyError(HttpStatus.NOT_FOUND+"","The user not exists."),HttpStatus.NOT_FOUND);
    }

    /***
     * Delete an existing user.
     * @param email user's email
     * @return
     */
    @DeleteMapping("/user/{email}")
    public Object delete(@PathVariable String email){
        com.vector.model.User user = this.userRepository.findByEmail(email);
        if(user!=null){
            this.userRepository.delete(user);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity(new MyError(HttpStatus.NOT_FOUND+"","The user not exists."),HttpStatus.NOT_FOUND);
    }
}
