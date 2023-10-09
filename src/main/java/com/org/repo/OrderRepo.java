package com.org.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.org.domain.Order;

public interface OrderRepo extends JpaRepository<Order, Integer> {

}
