package com.intellias.practice.controller;

import com.intellias.practice.exceptionHandling.customExceptions.NoMoneyException;
import com.intellias.practice.exceptionHandling.customExceptions.ProductNotFoundException;
import com.intellias.practice.exceptionHandling.customExceptions.UserNotFoundException;
import com.intellias.practice.model.Product;
import com.intellias.practice.model.Purchase;
import com.intellias.practice.model.User;
import com.intellias.practice.service.ProductService;
import com.intellias.practice.service.PurchaseService;
import com.intellias.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PurchaseController {
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;

    @GetMapping("/buyProduct")
    public String buyProduct(@RequestParam(value = "user_id") int userId,
                           @RequestParam(value = "product_id") int productId) throws UserNotFoundException, ProductNotFoundException, NoMoneyException {

        User user = userService.getUserById(userId);
        Product product = productService.getProductById(productId);

        buyProduct(user, product);
        return "Successful "+product.getName()+ " purchase";

    }


    private void buyProduct(User user, Product product) throws NoMoneyException {
        checkMoneyAmount(user.getMoneyAmount(), product.getPrice());
        withdrawMoney(user, product.getPrice());
        savePurchase(user, product);

    }

    private void checkMoneyAmount(double userMoney, double price) throws NoMoneyException {
        if (userMoney < price) throw new NoMoneyException();
    }

    private void withdrawMoney(User user, double moneyToWithdrew) {
        user.setMoneyAmount(user.getMoneyAmount() - moneyToWithdrew);

    }

    private void savePurchase(User user, Product product) {
        purchaseService.addNewPurchase(new Purchase(user, product));

    }
}
