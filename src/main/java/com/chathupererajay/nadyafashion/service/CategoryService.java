
package com.chathupererajay.nadyafashion.service;

import com.chathupererajay.nadyafashion.model.Category;
import com.chathupererajay.nadyafashion.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author CPere
 */
@Service
public class CategoryService {
  @Autowired
  CategoryRepository categoryRepository;
  
  public List<Category> getAllCategories(){
      return categoryRepository.findAll();
  }
  public void addCategory(Category cat){
      categoryRepository.save(cat);
  }
  
  public void removeCategoryById(int id){
      categoryRepository.deleteById(id);
  }
  
  public Optional<Category> getCategoryById(int id){
      return categoryRepository.findById(id);
  }
}
