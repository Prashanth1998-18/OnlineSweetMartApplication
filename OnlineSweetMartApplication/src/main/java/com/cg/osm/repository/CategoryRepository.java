package com.cg.osm.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.osm.entity.Category;
import com.cg.osm.entity.Product;

@Repository
public interface CategoryRepository  extends JpaRepository<Category,Integer> {
	
	@Query("from Category where :categoryid=categoryId")
	Optional<Category> findById(Category categoryid);
}