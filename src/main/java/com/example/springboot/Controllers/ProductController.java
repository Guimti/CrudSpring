package com.example.springboot.Controllers;


import com.example.springboot.Dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProducRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductController   {

   @Autowired
    ProducRepository producRepository;

    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@RequestBody @Valid ProductRecordDto productRecordDto ){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(producRepository.save(productModel));
    }

    @GetMapping("/products")
    public ResponseEntity<List <ProductModel>> getAllProducts(){

        return ResponseEntity.status(HttpStatus.OK).body(producRepository.findAll());

    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable(value = "id") UUID id){
        Optional<ProductModel> productO = producRepository.findById(id);
        if(productO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
    } return ResponseEntity.status(HttpStatus.OK).body(productO.get()); //get one
}



