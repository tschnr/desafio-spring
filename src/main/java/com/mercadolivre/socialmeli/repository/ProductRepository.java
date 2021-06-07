package com.mercadolivre.socialmeli.repository;


import com.mercadolivre.socialmeli.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository  extends JpaRepository<Products, Long> {
}
