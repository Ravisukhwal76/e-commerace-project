package com.ravi.major.service;

import com.ravi.major.entity.Categories;
import com.ravi.major.repository.CategoriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriesService {
    @Autowired
    CategoriesRepo categoriesRepo;

    public List<Categories> getCategories(){
        return categoriesRepo.findAll();
    }
    public void addCategory(Categories categories){
        categoriesRepo.save(categories);
    }

    public void removeById(int id){
        categoriesRepo.deleteById(id);
    }

    public Optional<Categories> getCategoryById(int id){
        return categoriesRepo.findById(id);
    }
}
