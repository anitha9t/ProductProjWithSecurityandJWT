package com.product.springsecurityjwts.Controller;

import com.product.springsecurityjwts.Entity.Product;
import com.product.springsecurityjwts.Entity.UserInfo;
import com.product.springsecurityjwts.Exception.ProductNotfoundException;
import com.product.springsecurityjwts.Pojo.AuthRequest;
import com.product.springsecurityjwts.Service.ProductService;
import com.product.springsecurityjwts.Service.UserInfoService;
import com.product.springsecurityjwts.ServiceImpl.JwtService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/products")
public class ProductController {
@Autowired
private ProductService productService;
    @Autowired
    private UserInfoService service;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/save")
    public ResponseEntity<Product> saveProduct(@Valid @RequestBody Product product) {
        return new ResponseEntity<Product>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/fetchAll")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<Product>> getAllProducts() {
        return new ResponseEntity<List<Product>>(productService.fetchAllProducts(), HttpStatus.CREATED);
    }

    @GetMapping("/fetch/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Product getProduct(@PathVariable Long id) throws ProductNotfoundException {
        return productService.fetchproduct(id);
    }
    @PostMapping("/newUser")
    public String addNewUser(@RequestBody UserInfo userInfo){
        return service.addUser(userInfo);
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getUsername());
        } else {
            throw new UsernameNotFoundException("invalid user request !");
        }


    }

}

