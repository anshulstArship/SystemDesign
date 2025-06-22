package com.main.userservice.services;

import com.main.userservice.dtos.LoginRequestDto;
import com.main.userservice.dtos.UserDto;
import com.main.userservice.models.Session;
import com.main.userservice.models.SessionStatus;
import com.main.userservice.models.User;
import com.main.userservice.repositories.SessionRepository;
import com.main.userservice.repositories.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    SessionRepository sessionRepository;

    public AuthService(UserRepository userRepository,SessionRepository sessionRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository=userRepository;
        this.sessionRepository=sessionRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
    }
    public UserDto login(String email,String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("Wrong username password");
        }
        String token = RandomStringUtils.randomAlphanumeric(30);
        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        return userDto;


    }
    public UserDto signUp(String email,String password){
        User user = new User();
        user.setEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);
        return userDto;

    }
}
