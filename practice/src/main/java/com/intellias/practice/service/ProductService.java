package com.intellias.practice.service;

import com.intellias.practice.exceptionHandling.customExceptions.ProductNotFoundException;
import com.intellias.practice.model.Product;
import com.intellias.practice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseService purchaseService;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public void deleteById(int id) throws ProductNotFoundException {
        try {

            getProductById(id);
            purchaseService.deleteAllByProductId(id);
            productRepository.deleteById(id);

        } catch (ProductNotFoundException | EmptyResultDataAccessException e) {
            throw new ProductNotFoundException();
        }

    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public Product getProductById(int id) throws ProductNotFoundException {
        return productRepository.findById(id).orElseThrow(ProductNotFoundException::new);
    }


}
