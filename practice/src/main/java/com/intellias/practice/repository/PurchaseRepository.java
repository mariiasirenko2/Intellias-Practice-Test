package com.intellias.practice.repository;

import com.intellias.practice.model.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    List<Purchase> findAllByUserId(int id);

    List<Purchase> findAllByProductId(int id);

    void deleteAllByUserId(int id);
    void deleteAllByProductId(int id);


}



