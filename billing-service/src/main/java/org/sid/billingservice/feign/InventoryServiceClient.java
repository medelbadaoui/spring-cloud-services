package org.sid.billingservice.feign;

import org.sid.billingservice.model.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "INVENTORY-SERVICE")
public interface InventoryServiceClient{
    @GetMapping("/products/{id}")
    Product findProductById(@PathVariable("id") Long id);
    @GetMapping("/products")
    PagedModel<Product> findAll();
}