package com.hotmail.mohamadi.meisam.springdatajpapractice;

import com.hotmail.mohamadi.meisam.springdatajpapractice.dao.ProductRepository;
import com.hotmail.mohamadi.meisam.springdatajpapractice.domain.Product;
import com.hotmail.mohamadi.meisam.springdatajpapractice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@SpringBootApplication
public class SpringDataJpaPracticeApplication implements CommandLineRunner {


    private final ProductService productService;

    public SpringDataJpaPracticeApplication(ProductService productService) {
        this.productService = productService;
    }

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpaPracticeApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
     //   productService.saveOneProduct();
//        productService.saveAllTest();
 //       productService.selectAll();
      //  productService.selectById(4);
       // productService.selectByName("iphone 13");
       // productService.selectByNameStartWithAndPriceLessThan();
      //  productService.deleteById(1);
//        Product p1 = Product.builder().id(3).name("samsung").build();
//        productService.delete(p1);
       // productService.updateNameById(5,"samsung s24 ultra");
       // productService.selectByNameInQueryMethod("iphone 7");
        //productService.selectByNameLikePattern("%p%");
       // productService.selectByNameContains("p");
        //productService.selectByNameStarts("iph");
       // productService.betweenTest(550 , 750);
      //  productService.selectByNameEqualsOrNameEquals("iphone 6" , "iphone 70");
      //  productService.selectAllBySqlTest();
       // productService.selectAllWithPaging();
//        productService.pagingInQueryMethod(1 , PageRequest.of(1, 3) );
//        System.out.println("**************************");
//        productService.pagingInQueryMethod(1000);

      //  productService.selectAllSortedProduct();
        productService.queryMethodAndSorting();
    }
}
