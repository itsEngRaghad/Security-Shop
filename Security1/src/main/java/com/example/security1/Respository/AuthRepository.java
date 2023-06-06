package com.example.security1.Respository;

import com.example.security1.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthRepository extends JpaRepository<MyUser,Integer> {


    MyUser findMyUserById(Integer id);
    MyUser findMyUserByUsername(String username);
}
