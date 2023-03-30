package com.app.crud.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.crud.products.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{
	
	//Query to find a product while its name
	@Query("SELECT p FROM Product p WHERE CONCAT(p.id, p.name, p.brand, p.madeIn) LIKE %?1%")
	public List<Product> findAll(String keyWord);

}
