package com.example.graphqlserver.Controller;

import com.example.graphqlserver.Entity.ProductEntity;
import com.example.graphqlserver.Repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class ProductController {

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @QueryMapping
    public ProductEntity productById(@Argument long id){

        var productGet = this.productRepository.findById(id);

        if (productGet.isPresent()){
            return productGet.get();
        }

        throw new RuntimeException("Nenhum produto com esse ID");

    }

    @MutationMapping
    public ProductEntity createProduct(@Argument String name, @Argument String description, @Argument Float price){

        ProductEntity productEntity = new ProductEntity(name, description, price);

        return productRepository.save(productEntity);
    }

    @MutationMapping
    public Boolean deleteProduct(@Argument long id){

        var getProductById = this.productRepository.findById(id);

        if (getProductById.isPresent()){

            this.productRepository.deleteById(id);
            return true;
        }

        return false;
    }

}