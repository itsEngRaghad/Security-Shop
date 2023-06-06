package com.example.security1.Service;

import com.example.security1.Model.MyUser;
import com.example.security1.Respository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {


    private final AuthRepository authRepository; //to arrive to DB

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MyUser myUser=authRepository.findMyUserByUsername(username);
        if(myUser==null){
            throw new UsernameNotFoundException("wrong username or password");
        }
        return myUser;
    }
}
