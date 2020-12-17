package org.sid.inventoryservice;
import org.sid.inventoryservice.entities.Product;
import org.sid.inventoryservice.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}
	@Bean
	CommandLineRunner start(ProductRepository productRepository,RepositoryRestConfiguration repositoryRestConfiguration){
		return args -> {
		repositoryRestConfiguration.exposeIdsFor(Product.class);
		productRepository.save(new Product(null,"Computer Desk Top HP",900));
		productRepository.save(new Product(null,"Printer Epson",80));
		productRepository.save(new Product(null,"MacBook Pro Lap Top",1800));
		productRepository.findAll().forEach(System.out::println);
		};
	}

}



