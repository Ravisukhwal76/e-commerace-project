package com.ravi.major.repository;

import com.ravi.major.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {
    public List<Product> findAllProductByCategories_Id(int id);
}
