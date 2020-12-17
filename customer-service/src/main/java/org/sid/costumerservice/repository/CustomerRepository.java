package org.sid.costumerservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.sid.costumerservice.entities.Customer;

@RepositoryRestResource
public
interface CustomerRepository extends JpaRepository<Customer,Long> { }
