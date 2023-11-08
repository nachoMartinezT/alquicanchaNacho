package com.alquicancha.services.impl;
import com.alquicancha.dtos.ProductDTO;
import com.alquicancha.entities.Photo;
import com.alquicancha.entities.Product;
import com.alquicancha.repositories.IProductRepository;
import com.alquicancha.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
    public  ProductDTO register(String name, String description, LocalDate fromDate, LocalDate toDate, String category) {

        Product product = new Product(name, description, fromDate, toDate, category);
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

    public void uploadProductImage(Long productId, MultipartFile photo) throws IOException {
        // Create a folder to store the photos
        String photosDir = "./src/main/resources/static/photos";
        File photosDirFile = new File(photosDir);
        if (!photosDirFile.exists()) {
            photosDirFile.mkdirs();
        }

        // Get the file from the request
        String fileName = photo.getOriginalFilename();
        byte[] bytes = photo.getBytes();

        // Write the file to the directory
        FileOutputStream fileOutputStream = new FileOutputStream(new File(photosDir, fileName));
        fileOutputStream.write(bytes);
        fileOutputStream.close();

        // Associate the photo with the product
        Product product = productRepository.findById(productId).orElse(null);
        Photo photoObj = new Photo();
        photoObj.setUrl(fileName);
        product.getPhotos().add(photoObj);
        productRepository.save(product);
    }

}
