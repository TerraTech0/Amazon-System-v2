package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MerchantStockService merchantStockService;
    private final ProductService productService;
    private final UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void addUsers(User user) {
        userRepository.save(user);
    }

    public boolean updateUser(Integer id, User user) {
        User u = userRepository.getById(id);
        if (u == null) {
            return false;
        }
        u.setUsername(user.getUsername());
        u.setPassword(user.getPassword());
        u.setBalance(user.getBalance());
        u.setRole(user.getRole());
        u.setEmail(user.getEmail());
        userRepository.save(u);
        return true;
    }

    public boolean deleteUser(Integer id) {
        User user = userRepository.getById(id);
        if (user == null) {
            return false;
        }
        userRepository.delete(user);
        return true;
    }

    public Optional<User> findById(Integer id) {
        return userRepository.findById(id);
    }

    public MerchantStock findMerchantStock(Integer productId, Integer merchantId) {
        for (MerchantStock merchantStock : merchantStockService.getAllMerchantStocks()) {
            if (merchantStock.getProductId().equals(productId) && merchantStock.getMerchantId().equals(merchantId)) {
                return merchantStock;
            }
        }
        return null;
    }

    public Product findProductById(Integer productId) {
        for (Product product : productService.getAllProducts()) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        System.out.println("Product with ID " + productId + " not found.");
        return null;
    }

    public String buyProduct(Integer userId, Integer productId, Integer merchantId) {
        Optional<User> user = findById(userId);
        if (!user.isPresent()) {
            return "User not found";
        }
        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("Product not found. Product ID: " + productId);
            return "Product not found";
        }
        MerchantStock merchantStock = findMerchantStock(productId, merchantId);
        if (merchantStock == null) {
            return "Merchant or product not found in merchant's stock";
        }
        if (merchantStock.getStock() <= 0) {
            return "Product not in stock";
        }
        if (user.get().getBalance() < product.getPrice()) {
            return "Not enough balance";
        }
        user.get().setBalance(user.get().getBalance() - product.getPrice());
        merchantStock.setStock(merchantStock.getStock() - 1);
        return "Successfully buy the product!";
    }


    public String rateProduct(Integer userId, Integer productId, Integer rating) {
        Optional<User> user = findById(userId);
        if (!user.isPresent()) {
            return "User not found";
        }

        Product product = findProductById(productId);
        if (product == null) {
            System.out.println("product is : " + productId);
            return "Product not found";
        }
        product.setRating(rating);
        return "Rating successful";
    }

    public List<Product> addProductToCart(Integer productId, Integer quantity) {
        List<Product> cart = new ArrayList<>();
        for (Product product : productService.getAllProducts()) {
            if (product.getProductId().equals(productId)) {
                for (int i = 0; i < quantity; i++) {
                    cart.add(product);
                }
                break;
            }
        }
        return cart;
    }

    public String addReview(Integer userId, Integer productId, String reviews) {
        Optional<User> user = findById(userId);
        if (!user.isPresent()) {
            return "User not found";
        }

        Product product = findProductById(productId);
        if (product == null) {
            return "Product not found";
        }
        if (product.getReviews() == null) {
            product.setReviews(new ArrayList<>());
        }
        product.getReviews().add(reviews);
        return "Review added successfully";
    }

}
