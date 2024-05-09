package com.example.springboot.Service;

import com.example.springboot.Dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProducRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProducRepository producRepository;

    public List<ProductModel> getAllProducts() {
        return producRepository.findAll();
    }

    public Optional<ProductModel> getProductById(Long id) {
        return producRepository.findById(id);
    }

    public ProductModel createProduct(ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return producRepository.save(productModel);
    }

    public ProductModel updateProduct(Long id, ProductRecordDto productRecordDto) {
        Optional<ProductModel> productO = producRepository.findById(id);
        if (productO.isEmpty()) {
            return null;
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return producRepository.save(productModel);
    }

    public void deleteProduct(Long id) {
        Optional<ProductModel> productO = producRepository.findById(id);
        productO.ifPresent(producRepository::delete);
    }
}
