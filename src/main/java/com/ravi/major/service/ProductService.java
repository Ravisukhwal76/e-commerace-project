package com.ravi.major.service;

import com.ravi.major.entity.Categories;
import com.ravi.major.entity.Product;
import com.ravi.major.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;
    public List<Product> getProducts(){
        return productRepo.findAll();
    }
    public void addProduct(Product product){
        productRepo.save(product);
    }

    public void removeById(long id){
        productRepo.deleteById(id);
    }

    public Optional<Product> getProductById(long id){
        return productRepo.findById(id);
    }

    public List<Product> getAllProductById(int id){
        return productRepo.findAllProductByCategories_Id(id);
    }
    public void deleteProductsByCategoryId(int categoryId) {
        List<Product> productsToDelete = productRepo.findAllProductByCategories_Id(categoryId);

        for (Product product : productsToDelete) {
            productRepo.deleteById(product.getId());
        }
    }
}
