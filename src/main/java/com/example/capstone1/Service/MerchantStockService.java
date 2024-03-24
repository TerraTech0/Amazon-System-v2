package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Repository.MerchantStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantStockService {


    private final CategoryService categoryService;
    private final MerchantService merchantService;
    private final MerchantStockRepository merchantStockRepository;



    public List<MerchantStock> getAllMerchantStocks(){
        return merchantStockRepository.findAll();
    }


    //fix this and add checkById instead this
    public String addMerchantStocks(MerchantStock merchantStock) {
        Optional<Merchant> merchant = merchantService.findById(merchantStock.getMerchantId());
        Optional<Category> category = categoryService.findById(merchantStock.getProductId());

        if (merchant.isPresent() && category.isPresent()){
            merchantStockRepository.save(merchantStock);
            return "done";
        }
        return "can't create!";
    }

    public Boolean updateMerhcantStocks(Integer id, MerchantStock merchantStock){
        MerchantStock mStock = merchantStockRepository.getById(id);
        if (mStock == null){
            return false;
        }
        mStock.setProductId(merchantStock.getProductId());
        mStock.setMerchantId(merchantStock.getMerchantId());
        mStock.setStock(merchantStock.getStock());

        merchantStockRepository.save(mStock);
        return true;
    }


    public Boolean deleteMerchantStock(Integer id){
        MerchantStock merchantStock = merchantStockRepository.getById(id);
        if (merchantStock == null){
            return null;
        }
        merchantStockRepository.delete(merchantStock);
        return true;
    }

    /*
    Create endpoint where user can add more stocks of product to a
    merchant Stock
        • this endpoint should accept a product id and merchant id and the amount of
            additional stock.
     */

    //check on productId and merchantId
    public boolean addStock(Integer productId, Integer merchantId, Integer amount) {
        List<MerchantStock> merchantStocks = merchantStockRepository.findAll();
        for (MerchantStock merchantStock : merchantStocks){
            if (merchantStock.getProductId().equals(productId) && merchantStock.getMerchantId().equals(merchantId)){
                int newStock = merchantStock.getStock() + amount;
                merchantStock.setStock(newStock);
                merchantStockRepository.save(merchantStock);
                return true;
            }
        }
        return false;
    }


    public Optional<MerchantStock> findById(Integer id){
        return merchantStockRepository.findById(id);
    }


}

