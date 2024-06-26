package com.example.tao.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.tao.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	
	
}
