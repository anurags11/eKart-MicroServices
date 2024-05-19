package com.infy.ekart.product.repository;

import org.springframework.data.repository.CrudRepository;

import com.infy.ekart.product.entity.Product;

//extends it with required Interface
public interface ProductRepository extends CrudRepository<Product, Integer>{

}
