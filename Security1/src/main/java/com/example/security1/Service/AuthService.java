package com.example.security1.Service;

import com.example.security1.Model.MyUser;
import com.example.security1.Respository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public List<MyUser>getAllUsers()
    {
        List<MyUser> myUsers= authRepository.findAll();
        return myUsers;
    }

    public void register(MyUser myUser){
        myUser.setRole("USER"); //to be role by default, don't let users make themselves as admins

       String hash=new BCryptPasswordEncoder().encode(myUser.getPassword()); //encrypt password first by hashing then set it to save
        myUser.setPassword(hash); //regular saving
        authRepository.save(myUser);
    }
}
