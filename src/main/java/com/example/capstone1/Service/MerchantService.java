package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Repository.MerchantRepository;
import com.example.capstone1.Repository.MerchantStockRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<Merchant> getAllMerchants(){
        return merchantRepository.findAll();
    }

    public void addMerchant(Merchant merchant) {
        merchantRepository.save(merchant);
    }


    public Boolean updateMerchant(Integer id, Merchant merchant){
        Merchant merchant1 = merchantRepository.getById(id);
        if (merchant1 == null){
            return null;
        }
        merchant1.setName(merchant.getName());
        merchantRepository.save(merchant1);
        return true;
    }

    public Boolean deleteMerchant(Integer id){
        Merchant merchant = merchantRepository.getById(id);
        if (merchant == null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }


    public Optional<Merchant> findById(Integer merchantId) {
        return merchantRepository.findById(merchantId);
    }
}
