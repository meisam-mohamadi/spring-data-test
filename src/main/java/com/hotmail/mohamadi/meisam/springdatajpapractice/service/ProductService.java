package com.hotmail.mohamadi.meisam.springdatajpapractice.service;

import com.hotmail.mohamadi.meisam.springdatajpapractice.dao.ProductRepository;
import com.hotmail.mohamadi.meisam.springdatajpapractice.domain.Product;
import org.springframework.beans.factory.annotation.Autowired;
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


    public void selectByNameStartWithAndPriceLessThan(){
        List<Product> result = repository.selectByNameStartWithAndPriceLessThan("iph", 1350);
        System.out.println(result);
    }

    public void deleteById(long id)
    {
        repository.deleteById(id);
    }

    public void delete(Product product)
    {
        repository.delete(product);
    }

    public void updateNameById(long id , String newName)
    {
        Optional<Product> optionalProduct = repository.findById(id);
        if(optionalProduct.isPresent())
        {
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


}
