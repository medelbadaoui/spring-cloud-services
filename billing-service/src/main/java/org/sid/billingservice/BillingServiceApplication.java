package org.sid.billingservice;

import org.sid.billingservice.repository.BillRepository;
import org.sid.billingservice.repository.ProductItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import org.sid.billingservice.entities.Bill;
import org.sid.billingservice.entities.ProductItem;
import org.sid.billingservice.feign.*;
import org.sid.billingservice.model.Customer;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BillingServiceApplication.class, args);
	}

	@Bean
	CommandLineRunner start(BillRepository billRepository, ProductItemRepository productItemRepository,
	InventoryServiceClient inventoryServiceClient, CustomerServiceClient customerServiceClient){
		return args -> {
			Bill bill=new Bill();
			bill.setBillingDate(new Date());
			Customer customer=customerServiceClient.findCustomerById(1L);
			bill.setCustomerID(customer.getId());
			billRepository.save(bill);
			inventoryServiceClient.findAll().getContent().forEach(p->{
			productItemRepository.save(new ProductItem(null,null,p.getId(),p.getPrice(),(int)(1+Math.random()*1000),bill));
			});
	};
	
}

@RestController
class BillRestController{
	@Autowired private BillRepository billRepository;
	@Autowired private ProductItemRepository productItemRepository;
	@Autowired private CustomerServiceClient customerServiceClient;
	@Autowired private InventoryServiceClient inventoryServiceClient;
	@GetMapping("/bills/full/{id}")
	Bill getBill(@PathVariable(name="id") Long id){
	Bill bill=billRepository.findById(id).get();
	bill.setCustomer(customerServiceClient.findCustomerById(bill.getCustomerID()));
	bill.setProductItems(productItemRepository.findByBillId(id));
	bill.getProductItems().forEach(pi->{
	pi.setProduct(inventoryServiceClient.findProductById(pi.getProductID()));
	});
	return bill; }
}
}
