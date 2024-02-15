package com.hotmail.mohamadi.meisam.springdatajpapractice.dao;

import com.hotmail.mohamadi.meisam.springdatajpapractice.domain.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    //select * from product where name = {name}
    Optional<Product> findByName(String name);


    //select * from product where name like 'iph%'


    List<Product> findByNameLike(String pattern);
    List<Product> findByNameContains(String aPartOfName);
    List<Product> findByNameStartsWith(String startOfName); // select * from product where name like 'iph%'


    //findByNameEndsWith
    //findByPriceLessThan
    //findByPriceGreaterThan
    //findByPriceGreaterThanEquals
    //List<Product> findByPriceGreaterThanEquals(int minPrice);

    List<Product> findByPriceBetween(int min , int max);

    //select * from product where name='iphone 6' or name='iphone 7 plus'
    List<Product> findByNameEqualsOrNameEquals(String n1 , String n2);
    List<Product> findByNameEqualsAndNameEquals(String n1 , String n2);
    List<Product> findByNameLikeOrPriceLessThanEqual(String n1 , int n2);
    List<Product> findByNameNot(String n1);

    List<Product> findTop3ByPriceLessThanOrderByPriceAsc(int price); //ascending


    @Query (value = "select * from product where price > ?1 order by name" , nativeQuery = true)
    List<Product> selectAllInSqlQuery(int minPrice);

    @Query (value = "select name from product" , nativeQuery = true)
    List<String> selectNameInSqlQuery();

   List<Product> findByPriceGreaterThan(int minPrice , Pageable p);
   List<Product> findByPriceLessThan(int maxPrice , Sort p);



}
