
package com.chathupererajay.nadyafashion.service;

import com.chathupererajay.nadyafashion.model.Product;
import com.chathupererajay.nadyafashion.repository.ProductRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CPere
 */
@Service
public class ProductService {
  @Autowired
  ProductRepository productRepository;
  
  public List<Product> getAllProducts(){
      return productRepository.findAll();
  }
//  public void addCategory(Product product){
//      productRepository.save(product);
//  }
  
//  public void removeCategoryById(int id){
//      productRepository.deleteById(id);
//  }
//  
//  public Optional<Category> getCategoryById(int id){
//      return productRepository.findById(id);
//  }
  
}
