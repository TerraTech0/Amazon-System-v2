package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Repository.CategoryRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {


    private final CategoryRepository categoryRepository;


    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public void addCategory(Category category){
        categoryRepository.save(category);
    }
//    public void addCategory(Category category) {
//        for (Category exist : categories) {
//            if (exist.getId().equalsIgnoreCase(category.getId())
//                    && exist.getName().equalsIgnoreCase(category.getName())) {
//                System.out.println("Category with this id is already exist!");
//                return;
//            }
//        }
//        categories.add(category);
//    }

    public Boolean updateCategory(Integer id, Category category){
        Category category1 = categoryRepository.getById(id);
        if (category1 == null){
            return false;
        }
        category1.setName(category.getName());
        return true;
    }

    public Boolean deleteCateogry(Integer id){
        Category category = categoryRepository.getById(id);
        if (category == null){
            return false;
        }
        categoryRepository.delete(category);
        return true;
    }

    public Optional<Category> findById(Integer productId) {
        return categoryRepository.findById(productId);
    }
}
