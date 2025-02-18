package com.example.capstone1.Controller;

import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.data.repository.config.RepositoryNameSpaceHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping("/get")
    public ResponseEntity getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        String result = productService.addProduct(product);
        if (result.equalsIgnoreCase("done")) {
            return ResponseEntity.ok("Product added successfully");
        } else {
            return ResponseEntity.badRequest().body("Can't add product with empty category");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (id != null) {
            productService.updateProduct(id, product);
            return ResponseEntity.status(200).body(new ApiResponse("product updated successfully!"));
        } else {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        if (id != null) {
            productService.deleteProduct(id);
            return ResponseEntity.status(200).body(new ApiResponse("product deleted successfully!"));
        } else {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        }

    }

    @GetMapping("find/{productId}")
    public ResponseEntity findProductById(@PathVariable Integer productId) {
        Optional<Product> prod = productService.findById(productId);
        return ResponseEntity.status(200).body(prod);
    }
}
