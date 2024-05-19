package com.infy.ekart.product.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infy.ekart.product.dto.ProductDTO;
import com.infy.ekart.product.entity.Product;
import com.infy.ekart.product.exception.EKartProductException;
import com.infy.ekart.product.repository.ProductRepository;


@Service (value ="customerProductService")
@Transactional
public class CustomerProductServiceImpl implements CustomerProductService {
	@Autowired
	private ProductRepository productRepository;

	@Override
	public List<ProductDTO> getAllProducts() throws EKartProductException {
		
		Iterable<Product> product = productRepository.findAll();
		List<ProductDTO> productList = new ArrayList<>();
		product.forEach(p -> {
			ProductDTO pdto = new ProductDTO();
			pdto.setAvailableQuantity(p.getAvailableQuantity());
			pdto.setBrand(p.getBrand());
			pdto.setCategory(p.getCategory());
			pdto.setDescription(p.getDescription());
			pdto.setName(p.getName());
			pdto.setPrice(p.getPrice());
			pdto.setProductId(p.getProductId());
			productList.add(pdto);
		});
		
		return productList;
	}

	@Override
	public ProductDTO getProductById(Integer productId) throws EKartProductException {
		
		Product p = productRepository.findById(productId).orElseThrow(()->new EKartProductException("ProductService.PRODUCT_NOT_AVAILABLE"));
		ProductDTO pdto = new ProductDTO();
			pdto.setAvailableQuantity(p.getAvailableQuantity());
			pdto.setBrand(p.getBrand());
			pdto.setCategory(p.getCategory());
			pdto.setDescription(p.getDescription());
			pdto.setName(p.getName());
			pdto.setPrice(p.getPrice());
			pdto.setProductId(p.getProductId());
		
		return pdto;
	}
		
	
	@Override
	public void reduceAvailableQuantity(Integer productId, Integer quantity) throws EKartProductException {
		Product prd = productRepository.findById(productId).orElseThrow(()-> new EKartProductException("ProductService.PRODUCT_NOT_AVAILABLE"));
		prd.setAvailableQuantity(prd.getAvailableQuantity()-quantity);
		
	}
}
