package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.userdao;
import com.app_quiz.backskeleton.models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class userservice {

    @Autowired
    private userdao userdao;

    public List<user> findAllUsers() {
        return userdao.findAll();
    }

    public Optional<user> findUserById(Long id) {
        return userdao.findById(id);
    }

    public user saveUser(user u) {
        return userdao.save(u);
    }

    public void deleteUser(Long id) {
        userdao.deleteById(id);
    }
}
