package com.intellias.practice.controller;


import com.intellias.practice.exceptionHandling.customExceptions.EmptyStringException;
import com.intellias.practice.exceptionHandling.customExceptions.NegativeMoneyValueException;
import com.intellias.practice.exceptionHandling.customExceptions.UserNotFoundException;
import com.intellias.practice.model.Product;
import com.intellias.practice.model.User;
import com.intellias.practice.service.PurchaseService;
import com.intellias.practice.service.UserService;
import com.intellias.practice.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @GetMapping("/userPurchases")
    public List<Product> getUserPurchases(@RequestParam(value = "user_id") int id) {
        return purchaseService.getAllUserPurchases(id);
    }

    @PostMapping("/addNewUser")
    public void addNewUser(@RequestBody User user) throws EmptyStringException, NegativeMoneyValueException {

        validateUserData(user);
        userService.addNewUser(user);

    }

    @DeleteMapping("/deleteUser")
    public void deleteById(@RequestParam(value = "user_id") int id) throws UserNotFoundException {
        userService.deleteById(id);
    }

    private void validateUserData(User user) throws EmptyStringException, NegativeMoneyValueException {

        ValidationUtils validator = new ValidationUtils();
        validator.emptyString(user.getFirstName());
        validator.emptyString(user.getLastName());
        validator.negativeNumber(user.getMoneyAmount());

    }
}
