
package com.chathupererajay.nadyafashion.controller;

import com.chathupererajay.nadyafashion.model.*;
import com.chathupererajay.nadyafashion.service.*;
import com.chathupererajay.nadyafashion.dto.ProductDTO;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author CPere
 */
@Controller
public class AdminController {
    public static String uploadDir = System.getProperty("user.dir") + 
                                     "/src/main/resources/static/productImages";

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
    @PostMapping("/admin/products/add")
    public String productAddPost(@ModelAttribute("productDTO")ProductDTO productDTO,
            @RequestParam("productImage")MultipartFile file,
            @RequestParam("imgName")String imgName) throws IOException{
        
        // generating the imageUUID
        String imageUUID;
        if(!file.isEmpty()){
            imageUUID = file.getOriginalFilename();
            Path fileNameAndPath = Paths.get(uploadDir, imageUUID);
            Files.write(fileNameAndPath, file.getBytes());
        }else{
            imageUUID = imgName;
        }
        Product product = new Product(productDTO.getId(), productDTO.getName(),
                          productDTO.getPrice(),productDTO.getDescription(),
                          imageUUID, categoryService.getCategoryById(productDTO.getCategoryId()).get());
        
        productService.addProduct(product);
        return "redirect:/admin/products";
    }
            
    //Delete Product
    @GetMapping("/admin/product/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.removeProductById(id);
        return "redirect:/admin/products";
    }

    @GetMapping("/admin/product/update/{id}")
    public String updateProduct(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id).get();
        ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(),
                product.getPrice(), product.getDescription(),
                product.getImageName(), product.getCategory().getId());

        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("productDTO", productDTO);
        return "productsAdd";
    }

    
    
    
}
