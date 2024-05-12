package com.example.tao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tao.entity.Product;
import com.example.tao.service.TaoService;

@RestController
public class TaoController {
	
	@Autowired
	TaoService taoService;
	
	@PostMapping(value="product")
	public ResponseEntity<Product> saveProduct(@RequestBody Product product){
		try {
			taoService.saveProductData(product);
			return new ResponseEntity<>(HttpStatusCode.valueOf(200));
		}catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
	}
	
	@GetMapping(value = "products")
    public ResponseEntity<List<Product>> getproducts(){
        try {
            return new ResponseEntity<>(taoService.getProducts(),HttpStatusCode.valueOf(200));
        }
        catch (Exception e){
            return new ResponseEntity<>(HttpStatusCode.valueOf(500));
        }
    }
	
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
	
	@GetMapping(value="/getProductByActiveStatus")
	public ResponseEntity<List<Product>> getProductByActiveStatus(){
		try {
			return new ResponseEntity<>(taoService.getProductByActiveStatus(),HttpStatusCode.valueOf(200));
		}catch (Exception e) {
		 return	new ResponseEntity<>(HttpStatusCode.valueOf(500));
		}
		
	}

}
