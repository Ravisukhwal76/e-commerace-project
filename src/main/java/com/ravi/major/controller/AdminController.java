package com.ravi.major.controller;

import com.ravi.major.dto.ProductDto;
import com.ravi.major.entity.Categories;
import com.ravi.major.entity.Product;
import com.ravi.major.service.CategoriesService;
import com.ravi.major.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class AdminController {

    public static String uploadDir = System.getProperty("user.dir") +"/src/main/resources/static/productImages";
    @Autowired
    CategoriesService categoriesService;

    @Autowired
    ProductService productService;

    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }

    @GetMapping("/admin/categories")
    public String getCategories(Model model){
        model.addAttribute("categories",categoriesService.getCategories());
        return "categories";
    }

    @GetMapping("admin/categories/add")
    public String addCategories(Model model){
        model.addAttribute("category",new Categories());
        return "categoriesAdd";
    }

    @PostMapping("admin/categories/add")
    public String postCategories(@ModelAttribute("category") Categories categories){
        categoriesService.addCategory(categories);
        return "redirect:/admin/categories";
    }

    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCategory(@PathVariable int id){
        productService.deleteProductsByCategoryId(id);
        categoriesService.removeById(id);
     return "redirect:/admin/categories";
    }

   @GetMapping("/admin/categories/update/{id}")
   public String updateCategory(@PathVariable int id,Model model){
       Optional<Categories> categories = categoriesService.getCategoryById(id);
       if(categories.isPresent()){
           model.addAttribute("category",categories.get());
           return "categoriesAdd";
       }
       return "404";
   }



   //product section




    @GetMapping("/admin/products")
    public String getProducts(Model model){
        model.addAttribute("products",productService.getProducts());
        return "products";
    }

    @GetMapping("/admin/products/add")
    public String addProduct(Model model){
        model.addAttribute("productDTO",new ProductDto());
        model.addAttribute("categories",categoriesService.getCategories());
        return "productsAdd";
    }

    @PostMapping("/admin/products/add")
    public String saveProduct(@ModelAttribute("productDTO")ProductDto productDto,
                              @RequestParam("productImage")MultipartFile file,
                              @RequestParam("imgName")String imgName) throws IOException {

     Product product = new Product();


     product.setId(productDto.getId());
     product.setName(productDto.getName());
     product.setCategories(categoriesService.getCategoryById(productDto.getCategoryId()).get());
     product.setDescription(productDto.getDescription());
     product.setPrice(productDto.getPrice());
     product.setWeight(productDto.getWeight());
     String imageUUID;

    if(!file.isEmpty()){
        imageUUID = file.getOriginalFilename();
        Path fileNameAndPath = Paths.get(uploadDir,imageUUID);
        Files.write(fileNameAndPath,file.getBytes());
    }
    else{
        imageUUID = imgName;
    }
      product.setImageName(imageUUID);

       productService.addProduct(product);

        return "redirect:/admin/products";
    }


    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable long id){
        productService.removeById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id,Model model){
         Product product = productService.getProductById(id).get();

         ProductDto productDto = new ProductDto();

         productDto.setId(product.getId());
         productDto.setName(product.getName());
         productDto.setCategoryId(product.getCategories().getId());
         productDto.setPrice(product.getPrice());
         productDto.setDescription(product.getDescription());
         productDto.setWeight(product.getWeight());
         productDto.setImageName(product.getImageName());


         model.addAttribute("categories",categoriesService.getCategories());
         model.addAttribute("productDTO",productDto);


//         if(categories.isPresent()){
//            model.addAttribute("category",categories.get());
//            return "productsAdd";
//        }
        return "productsAdd";
    }

}
