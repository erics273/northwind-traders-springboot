package com.pluralsight.northwind_traders_springboot.service;

import com.pluralsight.northwind_traders_springboot.model.Category;
import com.pluralsight.northwind_traders_springboot.model.Product;
import com.pluralsight.northwind_traders_springboot.model.Supplier;
import com.pluralsight.northwind_traders_springboot.repository.CategoryRepository;
import com.pluralsight.northwind_traders_springboot.repository.ProductRepository;
import com.pluralsight.northwind_traders_springboot.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    ProductRepository productRepository;
    CategoryRepository categoryRepository;
    SupplierRepository supplierRepository;

    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }

    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(int id) {
        return productRepository.findById(id);
    }

    public Product createProduct(Product product) {

        //if the category was sent in, then look it up to see if it exists
        //if it exists than add it to the product
        //if not then throw an exception so we can leverage that for the bad request response
        if (product.getCategory() != null) {

            //get the category id that was sent in the request
            int categoryId = product.getCategory().getCategoryId();

            //try to find the category, throw an exception if we cant find it
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            //if it exists, add it to the product
            product.setCategory(category);
        }

        if (product.getSupplier() != null) {
            int supplierId = product.getSupplier().getSupplierId();

            //try to find the category, throw an exception if we cant find it
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));

            //if it exists, add it to the product
            product.setSupplier(supplier);
        }

        //save the products to the db through the repository
        return productRepository.save(product);
    }

    public Product updateProduct(int id, Product updatedProduct) {

        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if(updatedProduct.getCategory() != null){
            int categoryId = updatedProduct.getCategory().getCategoryId();
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            existingProduct.setCategory(category);
        }else{
            existingProduct.setCategory(null);
        }

        if(updatedProduct.getSupplier() != null){
            int supplierId = updatedProduct.getSupplier().getSupplierId();
            Supplier supplier = supplierRepository.findById(supplierId)
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));
            existingProduct.setSupplier(supplier);
        }else{
            existingProduct.setSupplier(null);
        }

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setPrice(updatedProduct.getPrice());


        return productRepository.save(existingProduct);

    }

}
