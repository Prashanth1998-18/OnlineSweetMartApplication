package com.cg.osm.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cg.osm.entity.Product;
import com.cg.osm.error.CategoryNotFoundException;
import com.cg.osm.error.ProductNotFoundException;
import com.cg.osm.repository.CategoryRepository;
import com.cg.osm.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Product addProduct(Product product)throws CategoryNotFoundException {	
		int categoryId=product.getCategory().getCategoryId();
		boolean found=categoryRepository.existsById(categoryId);
		if(found) {
		return productRepository.save(product);
		}
		else {
			throw new CategoryNotFoundException("Invalid category");
		}
	}

	@Override
	public Product updateProduct(Product product)throws ProductNotFoundException,CategoryNotFoundException{
		
		int productId=product.getProdId();
		boolean found=productRepository.existsById(productId);
		int categoryId=product.getCategory().getCategoryId();
		boolean categoryfound=categoryRepository.existsById(categoryId);
		if(found)
		{
			if(categoryfound) {
			product.setProdName(product.getProdName());
			product.setProdPrice(product.getProdPrice());
			product.setExpDate(product.getExpDate());
			product.setCategory(product.getCategory());
			return productRepository.save(product);
			}
			else
				throw new CategoryNotFoundException("No such category found with id: "+categoryId);
		}
		else
		{
			throw new ProductNotFoundException("No such product found to update");
		}
		
	}

	@Override
	public String deleteProduct(int productId)throws ProductNotFoundException
	{
		boolean found=productRepository.existsById(productId);
		if(found)
		{
			productRepository.deleteByid(productId);
			return "Product has been deleted";
		}
		else
		{
			throw new ProductNotFoundException("No such product found to delete with id: "+productId);
		}
	}

	@Override
	public List<Product> showAllProducts()throws ProductNotFoundException
	{
		List<Product> prodlist=productRepository.findAll();
		if(prodlist.size()==0)
			{
				throw new ProductNotFoundException("No Products");
			}
		else
		{
			return prodlist;
		}
	}

	@Override
	public Optional<Product> findByProductId(int productId)throws ProductNotFoundException
	{
		Optional<Product> product= productRepository.findById(productId);
		if(product.isPresent())
		{
			return product;
		}
		else
		{
			throw new ProductNotFoundException("No Product found with id: "+productId);
		}
	}

	@Override
	public List<Product> findByCategoryId(int categoryId) throws CategoryNotFoundException,ProductNotFoundException{
		boolean found=categoryRepository.existsById(categoryId);
		if(found)
		{
			List<Product> productlist=productRepository.findByCategoryId(categoryId);
			return productlist;
		}
		else
		{
			throw new CategoryNotFoundException("No such category found");
		}
	

}
}