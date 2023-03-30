package com.app.crud.products.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.crud.products.entity.Product;
import com.app.crud.products.repository.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	//Method list to call all products
	/*Next to create the CRUD, we're going to modify the listAll method to add the searching field.
	 1. add String keyWord as a parameter
	 2. Create a conditional which compare if the parameter keyWord is not equal to null, next to that
	 return inside it the repository injection with the findAll method with the value keyword
	 3. Return next to the conditional the repository injection with the findAll method without a value*/
	public List<Product> listAll(String keyWord){
		if(keyWord != null) {
		return productRepository.findAll(keyWord);
		}
		return productRepository.findAll();
	}
	
	//Method to save products inside the database
	public void save(Product product) {
		productRepository.save(product);
	}
	
	//Method to filter a product by id
	public Product getProduct(Long id) {
		return productRepository.findById(id).get();
	}
	
	//Method to delete a product in the database
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
}
