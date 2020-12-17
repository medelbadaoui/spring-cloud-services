package org.sid.billingservice.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

import lombok.*;
import org.sid.billingservice.model.*;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bill {
      
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date billingDate;
    @Transient @OneToMany(mappedBy = "bill")
    private Collection<ProductItem> productItems;
    @Transient private Customer customer;
    private long customerID;

}
