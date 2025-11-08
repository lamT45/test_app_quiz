package com.app_quiz.backskeleton.services;

import com.app_quiz.backskeleton.DAO.UserDao;
import com.app_quiz.backskeleton.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDao userdao;

    public List<User> findAllUsers() {
        return userdao.findAll();
    }

    public Optional<User> findUserById(Long id) {
        return userdao.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userdao.findByEmail(email);
    }

    public User saveUser(User u) {
        return userdao.save(u);
    }

    public void deleteUser(Long id) {
        userdao.deleteById(id);
    }
}
