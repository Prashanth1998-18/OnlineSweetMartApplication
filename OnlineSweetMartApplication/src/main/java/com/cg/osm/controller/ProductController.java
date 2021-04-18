package com.cg.osm.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.service.CategoryServiceImpl;
import com.cg.osm.service.ProductService;

@RestController
public class ProductController {

	/*
	 * Injecting Product Service
	 */
	@Autowired
	private ProductService productservice;

	/*
	 * Injecting Category Service
	 */
	@Autowired
	private CategoryServiceImpl categoryservice;

	/*
	 * Retrieving all the products
	 * throw exception if there's none
	 */
	@GetMapping(path = "/product")
	public List<Product> showProducts() throws ProductNotFoundException {
		return productservice.showAllProducts();
	}

	/*
	 * Adding a product with validations 
	 * throw exception when the category mentioned in the Body of product doesn't exist
	 */
	@PostMapping(path = "/product")
	public Product addProduct(@RequestBody @Valid Product product) throws CategoryNotFoundException {
		return productservice.addProduct(product);
	}

	/*
	 * Updating an already existing product,
	 * if the product doesn't exist an exception is thrown
	 */
	@PutMapping(path = "/product")
	public Product updateProduct(@RequestBody @Valid Product product) throws ProductNotFoundException,CategoryNotFoundException {
		return productservice.updateProduct(product);
	}

	/*
	 * deleting a product from the database based on its ID
	 * throw exception if there's no such product present
	 */
	@DeleteMapping(path = "/product/{prodId}")
	public String deleteProduct(@PathVariable("prodId") int prodId) throws ProductNotFoundException {
		return productservice.deleteProduct(prodId);
	}

	/*
	 * Finding a product based on productID
	 * throw exception if the product is not present
	 */
	@GetMapping(path = "/product/{prodId}")
	public Product getProductById(@PathVariable("prodId") int prodId) throws ProductNotFoundException {
		Optional<Product> product = productservice.findByProductId(prodId);
			return product.get();
	}
	@GetMapping(path="/product/category/{id}")
	public List<Product> showProductsOnCategory(@PathVariable("id") int catid) throws ProductNotFoundException, CategoryNotFoundException
	{
		return productservice.findByCategoryId(catid);
	}

//===========================================================================================================================================//

	@GetMapping(path = "/category")
	public List<Category> showAllCategories()throws CategoryNotFoundException {
		return categoryservice.showAllCategories();
	}

	@PutMapping(path = "/category")
	public Category updateCategory(@RequestBody @Valid Category category) throws CategoryNotFoundException {
		return categoryservice.updateCategory(category);
	}

	@PostMapping(path = "/category")
	public Category addCategory(@RequestBody @Valid Category category) {
		return categoryservice.addCategory(category);
	}

	@DeleteMapping(path = "/category/{categoryId}")
	public String deleteCategoryById(@PathVariable("categoryId") int id) throws CategoryNotFoundException {
		return categoryservice.deleteCategoryById(id);

	}

	@GetMapping(path="/category/{categoryId}")
	public Category getCategoryById(@PathVariable("categoryId") int id) throws CategoryNotFoundException
	{
		Optional<Category> category=categoryservice.findByCategoryId(id);
		return category.get();
	}
}
