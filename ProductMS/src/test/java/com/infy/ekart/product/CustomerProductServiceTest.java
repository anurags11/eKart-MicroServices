package com.infy.ekart.product;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.entity.Product;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.repository.ProductRepository;
import com.infy.ekart.product.service.CustomerProductService;
import com.infy.ekart.product.service.CustomerProductServiceImpl;



@SpringBootTest
public class CustomerProductServiceTest {
	@Mock
	ProductRepository productRepository;
	
	@InjectMocks
	CustomerProductService customerProductService = new CustomerProductServiceImpl();
	
	
	@Test
	public void getAllProductsTestValidate() throws EKartProductException{
		
		List<Product> list = new ArrayList<>();
		Product obj = new Product();
		list.add(obj);
		
		Iterable<Product> iterable = list;
		Mockito.when(productRepository.findAll()).thenReturn(iterable);
		
		List<ProductDTO> lista = customerProductService.getAllProducts();
		Assertions.assertNotNull(lista);
	}
	
	@Test
	public void reduceAvailableQuantityTestInvalid() throws EKartProductException{
		Product id = new Product();
		id.setAvailableQuantity(40);
		Mockito.when(productRepository.findById(1000)).thenReturn(Optional.empty());
		EKartProductException message= Assertions.assertThrows(EKartProductException.class, ()->customerProductService.reduceAvailableQuantity(1000,Mockito.anyInt()));
		Assertions.assertEquals("ProductService.PRODUCT_NOT_AVAILABLE",message.getMessage());
	}

	@Test
	public void reduceAvailableQuantityTestValid() throws EKartProductException{
		Product id = new Product();
		id.setAvailableQuantity(60);
		Mockito.when(productRepository.findById(1000)).thenReturn(Optional.of(id));
		customerProductService.reduceAvailableQuantity(1000, 7);
		Assertions.assertEquals(53,id.getAvailableQuantity());
	}

	@Test
	public void getProductByIdtestValidate() throws EKartProductException{
		Product pdrt = new Product();
		Mockito.when(productRepository.findById(1)).thenReturn(Optional.of(pdrt));
		ProductDTO pdrDTO = customerProductService.getProductById(1);
		Assertions.assertNotNull(pdrDTO);		
	}
	
	@Test
	public void getProductByIdtestInvalidate() throws EKartProductException{
		int id = Mockito.anyInt();
		Mockito.when(productRepository.findById(id)).thenReturn(Optional.empty());
		EKartProductException message= Assertions.assertThrows(EKartProductException.class, ()->customerProductService.getProductById(1));
		Assertions.assertEquals("ProductService.PRODUCT_NOT_AVAILABLE", message.getMessage());
	}
	
}
