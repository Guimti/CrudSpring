package com.example.springboot.Controllers;

import com.example.springboot.Dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProducRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class ProductController {

    @Autowired
    ProducRepository producRepository;

    @Operation(summary = "Salvar um novo produto")
    @PostMapping("/products")
    public ResponseEntity<ProductModel> saveProduct(@Valid @RequestBody ProductRecordDto productRecordDto) {
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(producRepository.save(productModel));
    }

    @Operation(summary = "Obter todos os produtos")
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getAllProducts() {
        List<ProductModel> productsList = producRepository.findAll();
        if (!productsList.isEmpty()) {
            for (ProductModel product : productsList) {
                Long id = product.getIdProduct();
                product.add(linkTo(methodOn(ProductController.class).getOneProduct(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(productsList);
    }

    @Operation(summary = "Obter um produto específico pelo ID")
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOneProduct(@Parameter(description = "ID do produto a ser obtido") @PathVariable(value = "id") Long id) {
        Optional<ProductModel> productO = producRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(productO.get());
    }

    @Operation(summary = "Atualizar um produto existente pelo ID")
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@Parameter(description = "ID do produto a ser atualizado") @PathVariable(value = "id") Long id,
                                                @Valid @RequestBody ProductRecordDto productRecordDto) {

        Optional<ProductModel> productO = producRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = productO.get();
        BeanUtils.copyProperties(productRecordDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(producRepository.save(productModel));
    }

    @Operation(summary = "Excluir um produto pelo ID")
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteProduct(@Parameter(description = "ID do produto a ser excluído") @PathVariable(value = "id") Long id) {
        Optional<ProductModel> productO = producRepository.findById(id);
        if (productO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        producRepository.delete(productO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted successfully");
    }

}
