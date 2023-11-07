package com.alquicancha.services.impl;
import com.alquicancha.dtos.ProductDTO;
import com.alquicancha.entities.Photo;
import com.alquicancha.entities.Product;
import com.alquicancha.repositories.IProductRepository;
import com.alquicancha.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Set;

@Service
public class ProductServiceImpl implements IProductService{
    @Autowired
    IProductRepository productRepository;


    @Override
    public Set<ProductDTO> findAll() {
        return productRepository.findAll().stream().map(ProductDTO::new).collect(java.util.stream.Collectors.toSet());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product findProduct = productRepository.findById(id).orElse(null);
        ProductDTO productDTO = new ProductDTO(findProduct);
        return productDTO;
    }

    @Override
    public  ProductDTO register(String name, String description, LocalDate fromDate, LocalDate toDate) {

        Product product = new Product(name, description, fromDate, toDate);
        productRepository.save(product);

        ProductDTO productDTO = new ProductDTO(product);

        return productDTO;
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO update(Product product) {

       Product productToUpdate = productRepository.findById(product.getId()).orElse(null);
            productToUpdate.setName(product.getName());
            productToUpdate.setDescription(product.getDescription());
            productToUpdate.setFromDate(product.getFromDate());
            productToUpdate.setToDate(product.getToDate());
            productRepository.save(productToUpdate);
            return new ProductDTO(productToUpdate);

    }
}
