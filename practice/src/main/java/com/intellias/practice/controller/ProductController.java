package com.intellias.practice.controller;

import com.intellias.practice.exceptionHandling.customExceptions.EmptyStringException;
import com.intellias.practice.exceptionHandling.customExceptions.NegativeMoneyValueException;
import com.intellias.practice.exceptionHandling.customExceptions.ProductNotFoundException;
import com.intellias.practice.model.Product;
import com.intellias.practice.model.User;
import com.intellias.practice.service.ProductService;
import com.intellias.practice.service.PurchaseService;
import com.intellias.practice.validation.ValidationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }


    @GetMapping("/productPurchases")
    public List<User> getProductPurchases(@RequestParam(value = "product_id") int id) {
        return purchaseService.getAllProductPurchases(id);
    }

    @PostMapping("/addNewProduct")
    public void addNewProduct(@RequestBody Product product) throws NegativeMoneyValueException, EmptyStringException {

        validateProductData(product);
        productService.addNewProduct(product);
    }

    @DeleteMapping("/deleteProduct")
    public void deleteById(@RequestParam(value = "product_id") int id) throws ProductNotFoundException {
        productService.deleteById(id);
    }


    private void validateProductData(Product product) throws NegativeMoneyValueException, EmptyStringException {

        ValidationUtils validator = new ValidationUtils();
        validator.emptyString(product.getName());
        validator.negativeNumber(product.getPrice());

    }
}
