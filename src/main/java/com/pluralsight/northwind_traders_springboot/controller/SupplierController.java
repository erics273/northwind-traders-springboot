package com.pluralsight.northwind_traders_springboot.controller;

import com.pluralsight.northwind_traders_springboot.model.Category;
import com.pluralsight.northwind_traders_springboot.model.Supplier;
import com.pluralsight.northwind_traders_springboot.service.CategoryService;
import com.pluralsight.northwind_traders_springboot.service.SupplierService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    private SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public List<Supplier> getAllCategories(){
        return supplierService.getAllSuppliers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getCategoryById(@PathVariable int id){
        return supplierService.getSupplierById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Supplier> createCategory(@RequestBody Supplier supplier){
        try {
            Supplier createdSupplier = supplierService.createSuppler(supplier);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
