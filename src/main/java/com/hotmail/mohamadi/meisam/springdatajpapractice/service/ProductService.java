package com.hotmail.mohamadi.meisam.springdatajpapractice.service;

import com.hotmail.mohamadi.meisam.springdatajpapractice.dao.ProductRepository;
import com.hotmail.mohamadi.meisam.springdatajpapractice.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.w3c.dom.ls.LSOutput;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void saveOneProduct() {
        Product product = Product.builder()
                .name("iphone 8 plus")
                .price(600)
                .id(7)
                .build();

        repository.save(product);
    }

    public void saveAllTest() {
        List<Product> products = List.of(
                Product.builder().name("iphone 7 plus").price(700).build(),
                Product.builder().name("iphone 8").price(700).build()
        );
        repository.saveAll(products);
    }

    /**
     * select * from products
     */
    public void selectAll() {
        List<Product> allProducts = repository.findAll();
        System.out.println(allProducts);
    }

    public void selectById(long id) {
        Optional<Product> selectedProduct = repository.findById(id);
        Product result = selectedProduct.orElseThrow(() -> new RuntimeException("product with id " + id + " not found"));
        System.out.println(result);

    }

    public void selectByName(String name) {
        Optional<Product> product = repository.selectByName(name);
        product.ifPresentOrElse(
                (prd) -> System.out.println(prd),
                () -> System.out.println("product not found")
        );
    }


    public void selectByNameStartWithAndPriceLessThan() {
        List<Product> result = repository.selectByNameStartWithAndPriceLessThan("iph", 1350);
        System.out.println(result);
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void delete(Product product) {
        repository.delete(product);
    }

    public void updateNameById(long id, String newName) {
        Optional<Product> optionalProduct = repository.findById(id);
        if (optionalProduct.isPresent()) {
            optionalProduct.get().setName(newName);
            repository.save(optionalProduct.get());
        }
//        optionalProduct.ifPresentOrElse(
//                (prd)->{
//                    prd.setName(newName);
//                    repository.save(prd);
//                },
//                ()-> System.out.println("product not found")
//
//        );

    }

    public void selectByNameInQueryMethod(String name) {
        Product product = repository.findByName(name).get();
        System.out.println(product);
    }

    public void selectByNameLikePattern(String pattern) {
        List<Product> byNameLike = repository.findByNameLike(pattern);
        byNameLike.stream().forEach(prd -> System.out.println(prd));
    }

    public void selectByNameContains(String partOfName) {
        List<Product> byNameLike = repository.findByNameContains(partOfName);
        byNameLike.stream().forEach(prd -> System.out.println(prd));
    }

    public void selectByNameStarts(String startOfName) {
        List<Product> byNameLike = repository.findByNameStartsWith(startOfName);
        byNameLike.stream().forEach(prd -> System.out.println(prd));
    }

    public void betweenTest(int minimum, int maximum) {
        List<Product> byPriceBetween = repository.findByPriceBetween(minimum, maximum);
        System.out.println(byPriceBetween);
    }

    public void selectByNameEqualsOrNameEquals(String name1, String name2) {
        List<Product> byPriceBetween = repository.findByNameEqualsOrNameEquals(name1, name2);
        System.out.println(byPriceBetween);
    }

    public void selectAllBySqlTest() {
        List<Product> products = repository.selectAllInSqlQuery(800);
        System.out.println(products);
    }

    public void selectAllWithPaging() {
        Page<Product> all = repository.findAll(PageRequest.of(1, 3));
        all.forEach(prd -> System.out.println(prd));

    }

    public void pagingInQueryMethod(int minimumPrice, PageRequest pageRequest) {
        List<Product> byPriceGreaterThan = repository.findByPriceGreaterThan(minimumPrice, pageRequest);
        byPriceGreaterThan.forEach(prd -> System.out.println(prd));
    }

    public void pagingInQueryMethod(int minimumPrice) {
        List<Product> byPriceGreaterThan = repository.findByPriceGreaterThan(minimumPrice, null);
        byPriceGreaterThan.forEach(prd -> System.out.println(prd));
    }


    public void selectAllSortedProduct()
    {
//        List<Product> products = repository.findAll(Sort.by(Sort.Direction.DESC,"price","name"));
        List<Product> products = repository.findAll(Sort.by(Sort.Direction.DESC,"price")
                                               .and(Sort.by(Sort.Direction.ASC,"name")));
        products.forEach(prd -> System.out.println(prd));
    }

    public void queryMethodAndSorting()
    {
        List<Product> products = repository.findByPriceLessThan(900, Sort.by("price"));
        products.forEach(prd -> System.out.println(prd));

    }



}
