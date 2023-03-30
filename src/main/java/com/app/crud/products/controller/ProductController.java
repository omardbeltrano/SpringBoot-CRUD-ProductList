package com.app.crud.products.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.app.crud.products.entity.Product;
import com.app.crud.products.service.ProductService;

@Controller
public class ProductController {

	@Autowired(required = true)
	private ProductService productService;
	
	//Request to list and show all products on the main page
	
	/*Afterwards create the CRUD, and the showMain... method will be modified to add a searcher:
	 1. If you want to test first the app, add a String variable with a keyWord as a value to filter a product.
	 2. Next to that, add as showMain's parameter, the "Param" annotation with a keyWord as a parameter, and String variable
	 3. After test the app, put in comment the String keyWord that was created
	 4. */
	//@RequestMapping("/")
	@GetMapping("/")
	public String showMainPageWhotProducts(Model model, @Param("keyWord") String keyWord) {
		//String keyWord = "Apple";
		List<Product> productsList = productService.listAll(keyWord);
		
		model.addAttribute("productsList", productsList);
		model.addAttribute("keyWord", keyWord);
		return "index";
	}
	
	//@RequestMapping("/newProduct")
	@GetMapping("/newProduct")
	public String showFormAddProduct(Model model) {
		Product product = new Product();
		model.addAttribute("product", product);
		return "newProduct";
	}
	
	//@RequestMapping(value = "/saveProduct", method = RequestMethod.POST)
	@PostMapping(value = "/saveProduct")
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.save(product);
		return "redirect:/";
	}
	
	@RequestMapping("/editProduct/{id}")
	public ModelAndView editProduct(@PathVariable(name = "id") Long id) {
		//When I click on the edit button, ModelAndView redirects towards editProduct HTML view
		ModelAndView model = new ModelAndView("editProduct");
		Product product = productService.getProduct(id);
		model.addObject("product", product);
		return model;
	}
	
	@RequestMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable(name = "id") Long id) {
		productService.deleteProduct(id);
		return "redirect:/";
	}
}
