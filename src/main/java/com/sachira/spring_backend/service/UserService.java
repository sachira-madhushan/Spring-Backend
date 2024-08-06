package com.sachira.spring_backend.service;

import com.sachira.spring_backend.dto.LoginDTO;
import com.sachira.spring_backend.dto.LoginMessageDTO;
import com.sachira.spring_backend.dto.RegisterDTO;
import com.sachira.spring_backend.dto.UserDTO;
import com.sachira.spring_backend.entity.User;
import com.sachira.spring_backend.repo.UserRepo;
import com.sachira.spring_backend.utils.JWTAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Base64;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;

    @Autowired
    JWTAuthenticator jwtAuthenticator;

    public RegisterDTO registerUser(UserDTO userDTO) {
            byte[] encoded = Base64.getEncoder().encode(userDTO.getPassword().getBytes());
            String encodedPassword=new String(encoded);
            User user=userRepo.save(new User(userDTO.getUsername(),userDTO.getEmail(), encodedPassword));
            return new RegisterDTO(user.getId(),user.getUsername(),user.getEmail(),"Registration success");
    }

    public LoginMessageDTO loginUser(LoginDTO loginDTO) {
            Optional<User> user=userRepo.getUserByEmail(loginDTO.getEmail());

            if(user.isPresent()) {
                String token=jwtAuthenticator.generateJwtToken(user.get());
                byte[] decoded = Base64.getDecoder().decode(user.get().getPassword().getBytes());
                String decodedPassword=new String(decoded);
                if(decodedPassword.equals(loginDTO.getPassword())) {
                    return new LoginMessageDTO(user.get().getEmail(),"Login success",token);
                }else{
                    return new LoginMessageDTO(user.get().getEmail(),"Login failed");
                }
            }

            return new LoginMessageDTO(loginDTO.getEmail(),"Login failed");
    }
}
