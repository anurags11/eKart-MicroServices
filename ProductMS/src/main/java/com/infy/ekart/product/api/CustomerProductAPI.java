package com.infy.ekart.product.api;

import java.util.List;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.service.CustomerProductService;


@CrossOrigin
@Validated
@RestController
@RequestMapping(value = "/product-api")
public class CustomerProductAPI {

	
	private CustomerProductService customerProductService;

  
    private Environment  environment;
    
    
    Log logger = LogFactory.getLog(CustomerProductAPI.class);
	@GetMapping(value = "/products")
	public ResponseEntity<List<ProductDTO>> getAllProducts() throws EKartProductException {
		List<ProductDTO>productList = customerProductService.getAllProducts();
		return new ResponseEntity<>(productList,org.springframework.http.HttpStatus.OK);
	

	}
	
	@GetMapping(value = "/product/{productId}")
	public ResponseEntity<ProductDTO> getProductById(@PathVariable Integer productId) throws EKartProductException {
		
		ProductDTO product = customerProductService.getProductById(productId);
		return new ResponseEntity<>(product,org.springframework.http.HttpStatus.OK);
		
	}
	
	@PutMapping(value = "/update/{productId}")
	public ResponseEntity<String> reduceAvailableQuantity(@PathVariable Integer productId , @RequestBody Integer quantity) throws EKartProductException {
        
		customerProductService.reduceAvailableQuantity(productId, quantity);
		String successMessage= environment.getProperty("ProductAPI.REDUCE_QUANTITY_SUCCESSFULL");
		return new ResponseEntity<>(successMessage,org.springframework.http.HttpStatus.OK);

	}
}
