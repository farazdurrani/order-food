package com.restaurant.CSR;

import java.util.List;

import com.restaurant.CSR.ENTITY.Admin;
import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.SingleOrder;

public interface AdminMenuService {
	Admin authenticateAdmin(String username, String password);
	List<Customer> getOpenOrders();
	Customer getOpenOrder(int id);
	void setThisOrderClosed(long parameter);
	List<SingleOrder> getOpenSingleOrders();
	SingleOrder getOpenSingleOrder(int id);
	void setThisSingleOrderClosed(long parseLong);
	List<Customer> getClosedOrders();
	List<SingleOrder> getClosedSingleOrders();
	Customer getClosedOrder(int id);
	SingleOrder getClosedSingleOrder(int id);

	
}
