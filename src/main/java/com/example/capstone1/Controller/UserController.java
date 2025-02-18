package com.example.capstone1.Controller;


import com.example.capstone1.ApiResponse.ApiResponse;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Repository.UserRepository;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUsers() {
        return ResponseEntity.ok().body(userService.getAllUsers());
    }

    @PostMapping("/add")
    public ResponseEntity addUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addUsers(user);
        return ResponseEntity.status(200).body(new ApiResponse("user added successfully!"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        if (id != null) {
            userService.updateUser(id, user);
            return ResponseEntity.status(200).body("user updated successfully!");
        } else {
            return ResponseEntity.status(400).body("id doesn't exist!!!");
        }

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        if (id != null) {
            userService.deleteUser(id);
            return ResponseEntity.status(200).body("user deleted successfully!");
        } else {
            return ResponseEntity.status(400).body("Id doesn't Exist!!!");
        }

    }

    ///{userId}/{productId}/{merchantId}
    @PostMapping("/buyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity buyProduct(
            @PathVariable Integer userId,
            @PathVariable Integer productId,
            @PathVariable Integer merchantId) {
        return ResponseEntity.status(200).body(userService.buyProduct(userId, productId, merchantId));
    }


    @PostMapping("/rate-product/{userId}/{productId}/{rating}")
    public ResponseEntity<String> rateProduct(@PathVariable Integer userId,
                                              @PathVariable Integer productId,
                                              @PathVariable Integer rating) {
        String result = userService.rateProduct(userId, productId, rating);
        if (result.equals("Rating successful")) {
            return ResponseEntity.ok(result);
        } else {
            return ResponseEntity.badRequest().body(result);
        }
    }



    @GetMapping("/get-product/{productId}")
    public ResponseEntity findProductById(@PathVariable Integer productId) {
        return ResponseEntity.status(200).body(userService.findProductById(productId));
    }

    @PostMapping("/add-to-cart/{productId}/{quntity}")
    public ResponseEntity addProductToCart(@PathVariable Integer productId, @PathVariable Integer quntity) {
        List<Product> cart = userService.addProductToCart(productId, quntity);
        return ResponseEntity.status(200).body(cart);
    }

    @PostMapping("/review/{userId}/{productId}")
    public ResponseEntity addReview(@PathVariable Integer userId,
                                    @PathVariable Integer productId,
                                    @RequestBody String review,
                                    Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        userService.addReview(userId, productId, review);
        return ResponseEntity.status(200).body("review added successfully!");
    }

}
