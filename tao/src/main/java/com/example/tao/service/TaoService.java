package com.example.tao.service;

import com.example.tao.entity.Product;

import java.time.LocalDateTime;
import java.util.List;

public interface TaoService {
	
	List<Product> getProductByActiveStatus();
	List<Product> getProductByName(String productName);
	List<Product> getProductBypriceRange(int minPrice, int maxPrice);
	List<Product> getProductByDateRange(LocalDateTime minPostedDate, LocalDateTime maxPostedDate);
	Product saveProductData(Product product);
	Product updateProduct(Product product);
	void deleteProduct(Long id);
	List<Product> getproductsInApprovalQueue();
	void approveProduct(long productId);
	void rejectProduct(long productId);
	
}
