package com.example.graphqlserver.Controller;

import com.example.graphqlserver.Entity.ProductEntity;
import com.example.graphqlserver.Repository.ProductRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

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

    @QueryMapping
    public List<ProductEntity> productByName(@Argument String name){

        List<ProductEntity> getProductByName = this.productRepository.findByNameContainingIgnoreCase(name);

        if (getProductByName.isEmpty()){

            throw new RuntimeException("Nenhum nome encontrado");
        }

        return getProductByName;

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

    @MutationMapping
    public ProductEntity changeNameProduct(@Argument Long id, @Argument String name){

        Optional<ProductEntity> getProductById = this.productRepository.findById(id);

        if (getProductById.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        ProductEntity product = getProductById.get();

        product.setName(name);

        return this.productRepository.save(product);

    }

    @MutationMapping
    public ProductEntity changeDescriptionProduct(@Argument Long id, @Argument String description){

        Optional<ProductEntity> getProductById = this.productRepository.findById(id);

        if (getProductById.isEmpty()){
            throw new RuntimeException("Product not found");
        }

        ProductEntity product = getProductById.get();

        product.setDescription(description);

        return this.productRepository.save(product);

    }

    @MutationMapping
    public ProductEntity changePriceProduct(@Argument Long id, @Argument Float price){

        Optional<ProductEntity> getProductById = this.productRepository.findById(id);

        if (getProductById.isEmpty()){

            throw new RuntimeException("Product not found");

        }

        ProductEntity product = getProductById.get();

        product.setPrice(new BigDecimal(price.longValue()));

        return this.productRepository.save(product);

    }

}