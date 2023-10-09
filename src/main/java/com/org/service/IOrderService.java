package com.org.service;


import java.util.List;

import com.org.dto.OrderDTO;

public interface IOrderService {
	
	public String saveOrder(OrderDTO dto);
	
	public OrderDTO getOrderById(Integer id);
	
	public List<OrderDTO> getAllOrders();
	

}
