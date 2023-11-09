package com.alquicancha.controllers;

import com.alquicancha.dtos.ProductDTO;
import com.alquicancha.entities.Product;
import com.alquicancha.services.IProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

import java.util.Set;



@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private IProductService productService;

    @Operation(summary = "Get all products")
    @GetMapping("/products")
    public Set<ProductDTO> getProducts(){
        return productService.findAll();
    }

    @Operation(summary = "Get product by id")
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable Long id) {
        ProductDTO product = productService.findById(id);

        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(product);
    }


    @Operation(summary = "Register a product")
    @PostMapping("/products/register")
    public ResponseEntity<Object> register(@RequestParam String name,@RequestParam String description,
                                           @RequestParam LocalDate fromDate,@RequestParam LocalDate toDate,@RequestParam String category){
         productService.register(name, description, fromDate, toDate,category);

         return ResponseEntity.ok("Product created");
    }

    @Operation(summary = "Update a product")
    @PutMapping("/products/update")
    public ResponseEntity<Object> update(@RequestBody Product product){
        productService.update(product);

        return ResponseEntity.ok("Product updated");
    }

    @Operation(summary = "Delete a product")
    @ApiResponse(responseCode = "200", description = "Product deleted")
    @ApiResponse(responseCode = "404", description = "Product not found")
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @DeleteMapping("/products/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        productService.delete(id);

        return ResponseEntity.ok("Product deleted");
    }

    @Operation(summary = "Upload a product image")
    @PostMapping("/products/{productId}/upload")
    @ApiResponse(responseCode = "200", description = "Photo uploaded successfully")
    public ResponseEntity<Object> uploadProductImage(@PathVariable Long productId, @RequestParam("photo") MultipartFile photo) throws IOException {
        productService.uploadProductImage(productId, photo);

        return ResponseEntity.ok("Photo uploaded successfully");
    }


}
