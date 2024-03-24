package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {


    private final CategoryService categoryService;
    private final ProductRepository productRepository;


//    ArrayList<Product> products = new ArrayList<>();


    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public String addProduct(Product product){
        productRepository.save(product);
        return "done";
    }

    public Boolean updateProduct(Integer id, Product product){
        Product prod = productRepository.getById(id);
        if (prod == null){
            return false;
        }
        prod.setName(product.getName());
        prod.setPrice(product.getPrice());
        prod.setRating(product.getRating());
        prod.setCategoryID(product.getCategoryID());

        productRepository.save(prod);
        return true;
    }

    public Boolean deleteProduct(Integer id){
        Product product = productRepository.getById(id);
        if (product == null){
            return false;
        }
        productRepository.delete(product);
        return true;
    }

    public Optional<Product> findById(Integer id){
        return productRepository.findById(id);
    }

}
