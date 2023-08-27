package com.product.springsecurityjwts.ServiceImpl;


import com.product.springsecurityjwts.Entity.Product;
import com.product.springsecurityjwts.Exception.ProductNotfoundException;
import com.product.springsecurityjwts.Repository.ProductRepository;
import com.product.springsecurityjwts.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Primary
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    boolean flag;
    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> fetchAllProducts() {
        // TODO Auto-generated method stub
        return productRepository.findAll();
    }

    @Override
    public Product fetchproduct(Long id) throws ProductNotfoundException {
        Product product = null;
        if(id != null && id != 0) {
            flag = productRepository.existsById(id);
        }
        if(flag)
            product = productRepository.findById(id).get();
        else
            throw new ProductNotfoundException("product not saved");
        return product;

    }


}

