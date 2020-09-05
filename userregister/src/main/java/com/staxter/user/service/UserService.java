package com.staxter.user.service;

import com.staxter.user.exception.CheckUserPropertyNullValueException;
import com.staxter.user.exception.UserAlreadyExistException;
import com.staxter.user.model.User;
import com.staxter.user.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * This class responsible for implement register user
 */
@Service
@Slf4j
public class UserService {

    /**
     * using this repository for insert user in H2 {@link UserRepository}
     */
    @Autowired
    UserRepository userRepository;

    /**
     * This method responsible for register
     * @param user
     * @return
     */
    public User registerUser(User user)  {

        /**
         * This line of code check  user object that sent from client is a valid object or not. The validity conditions are :
         * 1- all of {@link User } property except hashedPassword should have value. (x.equals("") check this condition)
         * 2- all of {@link User } property except hashedPassword should be existed in sentObject. (x==null check this condition).
         * when each of above condition return true value this method return {@link CheckUserPropertyNullValueException}
         */
        Stream.of(user.getFirstName(),user.getLastName(),user.getPlainTextPassword(),user.getUserName()).forEach(x->{
           if(x==null || x.equals("")){
               throw new CheckUserPropertyNullValueException();
           }
        });
        /**
         * query user from H2 , return true if user exist
         */
        Optional<User> existUser=userRepository.findUserByUserName(user.getUserName());
        /**
         * if user with same userName exist, return {@link UserAlreadyExistException}
         */
        if(existUser.isPresent())
            throw new UserAlreadyExistException();

        /**
         * generate Hashed text with  BCryptPasswordEncoder ,using  user.getPlainTextPassword() String
         */
        PasswordEncoder encoder=new BCryptPasswordEncoder();
        user.setHashedPassword(encoder.encode(user.getPlainTextPassword()));
        /**
         * save user in H2
         */
        userRepository.save(user);
        log.info("User {} successfully inserted  ",user.getFirstName().concat(" ").concat(user.getLastName()));
        return user;
    }
}
