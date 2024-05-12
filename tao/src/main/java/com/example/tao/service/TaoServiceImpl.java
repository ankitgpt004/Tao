package com.example.tao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tao.entity.Product;
import com.example.tao.repo.TaoRepository;


@Service
public class TaoServiceImpl implements TaoService {
	
	@Autowired
	TaoRepository taoRepository;

	@Override
	public Product saveProductData(Product product) {
		try {
			if(product.getPrice()<10000 && product.getPrice()>5000) {
				product.setStatus("Approval");
				return taoRepository.save(product);
			}else {
				product.setStatus("Reject");
				return taoRepository.save(product);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return product;
		}
		 
	}

	@Override
	public List<Product> getProducts() {
		return taoRepository.findAll();
	}

	@Override
	public Product updateProduct(Product product) {
		return taoRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		taoRepository.deleteById(id);
		
	}

	@Override
	public List<Product> getProductByActiveStatus() {
		return taoRepository.getProductByActiveStatus();
	}
	
	

}
