package com.intellias.practice.service;

import com.intellias.practice.model.Product;
import com.intellias.practice.model.Purchase;
import com.intellias.practice.model.User;
import com.intellias.practice.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PurchaseService {
    @Autowired
    private PurchaseRepository purchaseRepository;

    public List<Product> getAllUserPurchases(int id) {

        return purchaseRepository.findAllByUserId(id).stream().map(Purchase::getProduct).toList();
    }

    public List<User> getAllProductPurchases(int id) {
        return purchaseRepository.findAllByProductId(id).stream().map(Purchase::getUser).toList();
    }

    public void addNewPurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
    }

    public void deleteAllByUserId(int id) {
        purchaseRepository.deleteAllByUserId(id);
    }

    public void deleteAllByProductId(int id) {
        purchaseRepository.deleteAllByProductId(id);
    }

}
