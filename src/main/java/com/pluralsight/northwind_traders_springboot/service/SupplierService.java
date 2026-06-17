package com.pluralsight.northwind_traders_springboot.service;

import com.pluralsight.northwind_traders_springboot.model.Category;
import com.pluralsight.northwind_traders_springboot.model.Supplier;
import com.pluralsight.northwind_traders_springboot.repository.SupplierRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {

    private SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSuppler(Supplier supplier){
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers(){
        return supplierRepository.findAll();
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.findById(id);
    }

}
