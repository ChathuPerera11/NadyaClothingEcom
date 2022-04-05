
package com.chathupererajay.nadyafashion.controller;

import com.chathupererajay.nadyafashion.model.*;
import com.chathupererajay.nadyafashion.service.*;
import com.chathupererajay.nadyafashion.dto.ProductDTO;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author CPere
 */
@Controller
public class AdminController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    ProductService productService;
      
    //Caregories    
    @GetMapping("/admin")
    public String adminHome(){
        return "adminHome";
    }
    
    @GetMapping("/admin/categories")
    public String getCategory(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "categories";
    }
    
    @GetMapping("/admin/categories/add")
    public String getCatAdd(Model model){
        model.addAttribute("category", new Category());
        return "categoriesAdd";
    }
    
    @PostMapping("/admin/categories/add")
    public String postCatAdd(@ModelAttribute ("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/admin/categories/delete/{id}")
    public String deleteCat(@PathVariable int id){
        categoryService.removeCategoryById(id);
        return "redirect:/admin/categories";
    }
    
    @GetMapping("/admin/categories/update/{id}")
    public String updateCat(@PathVariable int id, Model model){
       Optional<Category> cat = categoryService.getCategoryById(id);
       if(cat.isPresent()){
           model.addAttribute("category", cat.get());
           return "categoriesAdd";
       }else
           return "404";
    }
    
    //Products
    @GetMapping("/admin/products")
    public String getProducts(Model model){
       model.addAttribute("products", productService.getAllProducts());
       return "products";
    }
    
    @GetMapping("/admin/products/add")
    public String getProductsAdd(Model model){
       model.addAttribute("productDTO", new ProductDTO());
       model.addAttribute("categories", categoryService.getAllCategories());
       return "productsAdd";
    }
}

