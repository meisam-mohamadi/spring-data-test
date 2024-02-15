package com.hotmail.mohamadi.meisam.springdatajpapractice.dao;

import com.hotmail.mohamadi.meisam.springdatajpapractice.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product ,Long > {

    //select name , price from product
 //   select p.name , p.price from Product p where p.name = ?2 and p.price = ?1

    @Query("select p from Product p where p.name = :name")
    Optional<Product> selectByName(@Param("name") String esm);

    //select * from product where name like '?%' and price <?

    @Query("select p from Product p where p.price < :maximumPrice and p.name like concat(:startOfProductName,'%') ")
    List<Product> selectByNameStartWithAndPriceLessThan(  String startOfProductName ,  int maximumPrice);

}
