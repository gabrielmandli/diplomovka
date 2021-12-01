package com.mandli.diplomovka.repository;

import org.springframework.data.repository.CrudRepository;

import com.mandli.diplomovka.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
