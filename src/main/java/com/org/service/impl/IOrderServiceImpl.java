package com.org.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.org.domain.Order;
import com.org.dto.OrderDTO;
import com.org.exception.IdNotFoundException;
import com.org.repo.OrderRepo;
import com.org.service.IOrderService;

@Service
public class IOrderServiceImpl implements IOrderService {

	@Autowired
	OrderRepo orderRepo;

	@Override
	public String saveOrder(OrderDTO dto) {
		Order entity = new Order();
		BeanUtils.copyProperties(dto, entity);
		orderRepo.save(entity);
		System.out.println(entity);
		return "success fully saved...";
	}

	@Override
	public OrderDTO getOrderById(Integer id) {
		Optional<Order> obj = orderRepo.findById(id);
		if (obj.isPresent()) {
			Order order = obj.get();
			OrderDTO dto = new OrderDTO();
			BeanUtils.copyProperties(order, dto);
			return dto;
		}

		throw new IdNotFoundException("Id not found :" + id);
	}

	@Override
	public List<OrderDTO> getAllOrders() {
		List<Order> all = orderRepo.findAll();
		List<OrderDTO> dtos = new ArrayList<>();
		for (Order entity : all) {
			OrderDTO dto = new OrderDTO();
			BeanUtils.copyProperties(entity, dto);
			dtos.add(dto);
		}
		return dtos;
	}

}
