package com.example.tao.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tao.entity.ApprovalQueue;
import com.example.tao.entity.Product;
import com.example.tao.repo.ApprovalQueueRepository;
import com.example.tao.repo.ProductRepository;


@Service
public class TaoServiceImpl implements TaoService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ApprovalQueueRepository approvalQueueRepository;
	
	@Override
	public List<Product> getProductByActiveStatus() {
		List<Product> productList = productRepository.findAll();
		return productList.stream().sorted(Comparator.comparing(Product::getCreatedDate)).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByName(String productName) {
		List<Product> productList = productRepository.findAll();
		return productList.stream().filter(p -> p.getProductName().equals(productName)).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductBypriceRange(int minPrice, int maxPrice) {
		List<Product> productList = productRepository.findAll();
		return productList.stream().filter(p-> p.getPrice()>minPrice && p.getPrice()<maxPrice).collect(Collectors.toList());
	}
	
	@Override
	public List<Product> getProductByDateRange(LocalDateTime minPostedDate, LocalDateTime maxPostedDate) {
		List<Product> productList = productRepository.findAll();
		return productList.stream().filter(p->p.getCreatedDate().isAfter(minPostedDate) && p.getCreatedDate().isBefore(maxPostedDate)).collect(Collectors.toList());
	}

	@Override
	public Product saveProductData(Product product) {
		try {
			if(product.getPrice()<10000 && product.getPrice()>5000) {
				product.setStatus("Approval queue");
				product.setCreatedDate(LocalDateTime.now());
				ApprovalQueue approvalQueue =new ApprovalQueue();
				approvalQueue.setProductId(product.getProductId());
				approvalQueue.setProductName(product.getProductName());
				approvalQueueRepository.save(approvalQueue);
				return productRepository.save(product);
			}else {
				return null;
			}
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Product updateProduct(Product product) {
		Optional<Product> p1 = productRepository.findById(product.getProductId());
		if(product.getPrice() > p1.get().getPrice()/2) {
			product.setStatus("Approval queue");
			ApprovalQueue approvalQueue =new ApprovalQueue();
			approvalQueue.setProductId(product.getProductId());
			approvalQueue.setProductName(product.getProductName());
			approvalQueueRepository.save(approvalQueue);
		}
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(Long id) {
		Optional<Product> p1 = productRepository.findById(id);
		p1.get().setStatus("Approval queue");
		ApprovalQueue approvalQueue =new ApprovalQueue();
		approvalQueue.setProductId(p1.get().getProductId());
		approvalQueue.setProductName(p1.get().getProductName());
		approvalQueueRepository.save(approvalQueue);
		productRepository.deleteById(id);
		
	}
	
	@Override
	public List<Product> getproductsInApprovalQueue() {
		return productRepository.findAll().stream().filter(p->p.getStatus().equals("Approval queue"))
				.sorted(Comparator.comparing(Product::getCreatedDate)).collect(Collectors.toList());
	}

	@Override
	public void approveProduct(long productId) {
		Optional<Product> p1 = productRepository.findById(productId);
		p1.get().setStatus("Approved");
		approvalQueueRepository.deleteById(productId);
		productRepository.save(p1.get());
	}

	@Override
	public void rejectProduct(long productId) {
		Optional<Product> p1 = productRepository.findById(productId);
		p1.get().setStatus("Reject");
		approvalQueueRepository.deleteById(productId);
		productRepository.save(p1.get());
		
	}

	

	

	
	
	

}
