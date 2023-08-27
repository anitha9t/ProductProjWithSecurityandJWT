package com.product.springsecurityjwts.Service;

import com.product.springsecurityjwts.Entity.Product;
import com.product.springsecurityjwts.Exception.ProductNotfoundException;

import java.util.List;

public interface ProductService {
    public Product saveProduct(Product product);
    public List< Product> fetchAllProducts();
    public Product  fetchproduct (Long id )throws ProductNotfoundException;


}
