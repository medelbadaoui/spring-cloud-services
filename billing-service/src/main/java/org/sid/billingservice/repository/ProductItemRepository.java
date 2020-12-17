package org.sid.billingservice.repository;

import java.util.*;

import org.sid.billingservice.entities.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ProductItemRepository extends
    JpaRepository<ProductItem,Long>{
        List<ProductItem> findByBillId(Long billID);
        
}
