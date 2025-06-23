package com.main.userservice.services;

import com.main.userservice.dtos.LoginRequestDto;
import com.main.userservice.dtos.UserDto;
import com.main.userservice.models.Session;
import com.main.userservice.models.SessionStatus;
import com.main.userservice.models.User;
import com.main.userservice.repositories.SessionRepository;
import com.main.userservice.repositories.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.MacAlgorithm;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMapAdapter;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
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
    public ResponseEntity<UserDto> login(String email, String password){
        Optional<User> userOptional = userRepository.findByEmail(email);
        if(userOptional.isEmpty()){
            return null;
        }
        User user = userOptional.get();
        if(!bCryptPasswordEncoder.matches(password,user.getPassword())){
            throw new RuntimeException("Wrong username password");
        }
        String token1 = RandomStringUtils.randomAlphanumeric(30);

        // Create JWT Token



        MacAlgorithm alg = Jwts.SIG.HS256; //or HS384 or HS256
        SecretKey key = alg.key().build();
//
//        String message =  "{\n" +
//                "  \"email\": \"anshul@gmail.com\",\n" +
//                "  \"name\": \"John Doe\",\n"+
//                "}";
//        byte[] content = message.getBytes(StandardCharsets.UTF_8);

        Map<String,Object> jsonForJwt = new HashMap<>();
        jsonForJwt.put("email",user.getEmail());
        jsonForJwt.put("roles",user.getRoles());
        jsonForJwt.put("createdAt",new Date());
        jsonForJwt.put("expiryAt", new Date(LocalDate.now().plusDays(3).toEpochDay()));

// Create the compact JWS:
        String token = Jwts.builder().claims(jsonForJwt).signWith(key, alg).compact();

// Parse the compact JWS:
 //       content = Jwts.parser().verifyWith(key).build().parseSignedContent(token).getPayload();



        MultiValueMapAdapter<String,String> headers = new MultiValueMapAdapter<>(new HashMap<>());
        headers.add(HttpHeaders.SET_COOKIE,"auth-token:"+token);

        Session session = new Session();
        session.setToken(token);
        session.setUser(user);
        session.setStatus(SessionStatus.ACTIVE);
        sessionRepository.save(session);
        UserDto userDto = new UserDto();
        userDto.setEmail(email);

        ResponseEntity<UserDto> response = new ResponseEntity<>(userDto,headers, HttpStatus.OK);
        return response;


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
    public SessionStatus validateToken(Long userId,String token){
        Optional<Session> sessionOptional = sessionRepository.findByTokenAndUser_Id(token,userId);
        if(sessionOptional.isEmpty()){
            return SessionStatus.ENDED;
        }
        Session session = sessionOptional.get();
        if(!session.getStatus().equals(SessionStatus.ACTIVE)){
            return SessionStatus.ENDED;
        }
        Jws<Claims> jwsClaims = Jwts.parser().build().parseSignedClaims(token);
        String email =(String) jwsClaims.getPayload().get("email");// can validate based on multiple data
        return SessionStatus.ACTIVE;

    }
}
