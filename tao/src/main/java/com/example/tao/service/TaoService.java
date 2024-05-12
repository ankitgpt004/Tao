package com.example.tao.service;

import com.example.tao.entity.Product;
import java.util.List;

public interface TaoService {
	
	Product saveProductData(Product product);
	List<Product> getProducts();
	Product updateProduct(Product product);
	void deleteProduct(Long id);
	List<Product> getProductByActiveStatus();

}
