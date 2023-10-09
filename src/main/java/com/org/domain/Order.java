package com.org.domain;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Oder_dtls")
public class Order {

	@Id
	@GeneratedValue
	private Integer orderId;

	private String orderName;

	private String address;
	
	
	@CreationTimestamp
	private Date orderDate;

}
