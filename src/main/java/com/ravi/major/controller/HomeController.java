package com.ravi.major.controller;
 
import com.ravi.major.global.GlobalData;
import com.ravi.major.service.CategoriesService; 
import com.ravi.major.service.ProductService;
import com.ravi.major.service.TaskAssignService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;
    
    
    @Autowired
    private CategoriesService categoriesService;
    //private TaskAssignService taskAssignService;

    @GetMapping({"/","/home"})
    public String Home(Model model){
        model.addAttribute("cartCount", GlobalData.cart.size());
        return "index";
    }
    
    
  
    //Task Management 

//    @GetMapping("/shop")
//    public String shop(Model model){
//     model.addAttribute("categories",categoriesService.getCategories());
//     model.addAttribute("products",productService.getProducts());
//     model.addAttribute("cartCount",GlobalData.cart.size());
//        return "shop";
//    }
    
    
    
    @GetMapping("/myTask")
    public String myTask(Model model){
      	
        model.addAttribute("categories",categoriesService.getCategories());
        model.addAttribute("products",productService.getProducts());
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "myTask";
    }
    
    
    @GetMapping("/shop/category/{id}")
    public String shopByCategory(Model model, @PathVariable int id){
        model.addAttribute("categories",categoriesService.getCategories());
        model.addAttribute("products",productService.getAllProductById(id));
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "shop";
    }	
    @GetMapping("/shop/viewproduct/{id}")
    public String viewProduct(Model model, @PathVariable long id){
        model.addAttribute("product",productService.getProductById(id).get());
        model.addAttribute("cartCount",GlobalData.cart.size());
        return "viewProduct";
    }

 }
