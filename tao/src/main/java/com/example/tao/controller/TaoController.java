package com.example.tao.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tao.entity.Product;
import com.example.tao.service.TaoService;

@RestController
@RequestMapping("/api")
public class TaoController {
	
	@Autowired
	TaoService taoService;
	
	/*
	 * API to List Active Products:
	 */
	@GetMapping(value="/products")
	public ResponseEntity<List<Product>> getProductByActiveStatus(){
		try {
			return new ResponseEntity<>(taoService.getProductByActiveStatus(),HttpStatusCode.valueOf(200));
		}catch (Exception e) {
			return	new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}
	
	/*
	 * API to Search Products on product Name:
	 */
	@GetMapping(value="/products/search/{productName}")
	public ResponseEntity<List<Product>> getProductByName(@PathVariable String productName){
		try {
			return new ResponseEntity<>(taoService.getProductByName(productName),HttpStatusCode.valueOf(200));
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}
	
	/*
	 * API to Search Products on product price range:
	 */
	@GetMapping(value="/products/search/{minPrice}/{maxPrice}")
	public ResponseEntity<List<Product>> getProductByPriceRange(@PathVariable int minPrice, @PathVariable int maxPrice){
		try {
			return new ResponseEntity<>(taoService.getProductBypriceRange(minPrice,maxPrice),HttpStatusCode.valueOf(200));
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
		
	}
	
	/*
	 * API to Search Products on product date range:
	 */
	@GetMapping(value="/products/search/{minPostedDate}/{maxPostedDate}")
	public ResponseEntity<List<Product>> getProductByDateRange(@PathVariable LocalDateTime minPostedDate, @PathVariable LocalDateTime maxPostedDate){
		try {
			return new ResponseEntity<>(taoService.getProductByDateRange(minPostedDate,maxPostedDate),HttpStatusCode.valueOf(200));
		}catch (Exception e) {
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
		
	}
	
	/*
	 * API to Create a Product:
	 */
	@PostMapping(value="/product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		try {
			taoService.saveProductData(product);
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}
	
	/*
	 * API to Update a Product:
	 */
	@PutMapping(value = "/updateProduct")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product){
        try {
            taoService.updateProduct(product);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
	/*
	 * API to Delete a Product:
	 */
	@DeleteMapping(value = "/deleteProduct")
    public ResponseEntity<Product> updateUserData(@RequestParam(value = "productId") Long productId){
        try {
            taoService.deleteProduct(productId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
	/*
	 * API to Get Products in Approval Queue:
	 */
	@GetMapping(value = "/products/approval-queue")
    public ResponseEntity<List<Product>> getproductsInApprovalQueue(){
        try {
            return new ResponseEntity<>(taoService.getproductsInApprovalQueue(),HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
	/*
	 * API to Approve a Product:
	 */
	@PutMapping(value = "/products/approval-queue/{productId}/approve")
    public ResponseEntity<Product> approveProduct(@PathVariable long productId){
        try {
            taoService.approveProduct(productId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
	/*
	 * API to Reject a Product:
	 */
	@PutMapping(value = "/products/approval-queue/{productId}/reject")
    public ResponseEntity<Product> rejectProduct(@PathVariable long productId){
        try {
            taoService.rejectProduct(productId);
            return new ResponseEntity<>(HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
	
	

}
