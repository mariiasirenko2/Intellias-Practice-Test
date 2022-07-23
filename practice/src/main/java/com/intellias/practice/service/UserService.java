package com.intellias.practice.service;

import com.intellias.practice.exceptionHandling.customExceptions.ProductNotFoundException;
import com.intellias.practice.exceptionHandling.customExceptions.UserNotFoundException;
import com.intellias.practice.repository.UserRepository;
import com.intellias.practice.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseService purchaseService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteById(int id) throws UserNotFoundException {

        try {
            getUserById(id);
            purchaseService.deleteAllByUserId(id);
            userRepository.deleteById(id);

        } catch (UserNotFoundException | EmptyResultDataAccessException e) {
            throw new UserNotFoundException();
        }
    }

    public void addNewUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(int id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

}
