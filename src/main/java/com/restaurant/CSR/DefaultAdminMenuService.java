package com.restaurant.CSR;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.CSR.ENTITY.Admin;
import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.SingleOrder;

@Service
public class DefaultAdminMenuService implements AdminMenuService {

	@Autowired OrderRepository orderRepository;
	
	@Override
	public List<Customer> getOpenOrders(){
		List<Customer> ok = this.orderRepository.getOpenOrders();
		List<Customer> noDuplicates = new ArrayList<Customer>();
		for (int i = 0; i < ok.size(); i++) {
		noDuplicates.add(new Customer(ok.get(i)));
		noDuplicates.get(i).setId(ok.get(i).getId());
		}
		Set<Customer> set = new LinkedHashSet<Customer>();
		set.addAll(noDuplicates);
		noDuplicates.clear();
		noDuplicates.addAll(set);
		Collections.reverse(noDuplicates);

		return noDuplicates;
	}
	


	@Override
	public Admin AdminAuthentication(String username, String password) {
		
		return this.orderRepository.AdminAuthentication(username, password);
	}

	@Override
	public Customer getOpenOrder(int id) {
		
		List<Customer> ok = this.orderRepository.getOpenOrders();
		for (int i = 0; i < ok.size(); i++) {
			if(ok.get(i).getId() == id)
				return ok.get(i);
		}
		
		return null;
	}



	@Override
	public void setThisOrderClosed(long id) {
		this.orderRepository.setThisOrderClose(id);
		
	}



	@Override
	public List<SingleOrder> getOpenSingleOrders() {
		List<SingleOrder> ok = this.orderRepository.getOpenSingleOrders();
		List<SingleOrder> noDuplicates = new ArrayList<SingleOrder>();
		for (int i = 0; i < ok.size(); i++) {
		noDuplicates.add(new SingleOrder(ok.get(i)));
		noDuplicates.get(i).setId(ok.get(i).getId());
		}
		Set<SingleOrder> set = new LinkedHashSet<SingleOrder>();
		set.addAll(noDuplicates);
		noDuplicates.clear();
		noDuplicates.addAll(set);
		Collections.reverse(noDuplicates);
		return noDuplicates;
	}



	@Override
	public SingleOrder getOpenSingleOrder(int id) {
		List<SingleOrder> ok = this.orderRepository.getOpenSingleOrders();
		for (int i = 0; i < ok.size(); i++) {
			if(ok.get(i).getId() == id)
				return ok.get(i);
		}
		
		return null;
	}



	@Override
	public void setThisSingleOrderClosed(long id) {
		this.orderRepository.setThisSingleOrderClose(id);		
	}



	@Override
	public List<Customer> getClosedOrders() {
		List<Customer> ok = this.orderRepository.getClosedOrders();
		List<Customer> noDuplicates = new ArrayList<Customer>();
		for (int i = 0; i < ok.size(); i++) {
		noDuplicates.add(new Customer(ok.get(i)));
		noDuplicates.get(i).setId(ok.get(i).getId());
		}
		Set<Customer> set = new LinkedHashSet<Customer>();
		set.addAll(noDuplicates);
		noDuplicates.clear();
		noDuplicates.addAll(set);
		Collections.reverse(noDuplicates);
		return noDuplicates;
	}



	@Override
	public List<SingleOrder> getClosedSingleOrders() {
		List<SingleOrder> ok = this.orderRepository.getClosedSingleOrders();
		List<SingleOrder> noDuplicates = new ArrayList<SingleOrder>();
		for (int i = 0; i < ok.size(); i++) {
		noDuplicates.add(new SingleOrder(ok.get(i)));
		noDuplicates.get(i).setId(ok.get(i).getId());
		}
		Set<SingleOrder> set = new LinkedHashSet<SingleOrder>();
		set.addAll(noDuplicates);
		noDuplicates.clear();
		noDuplicates.addAll(set);
		Collections.reverse(noDuplicates);
		return noDuplicates;
	}



	@Override
	public Customer getClosedOrder(int id) {
		List<Customer> ok = this.orderRepository.getClosedOrders();
		for (int i = 0; i < ok.size(); i++) {
			if(ok.get(i).getId() == id)
				return ok.get(i);
		}
		
		return null;
	}



	@Override
	public SingleOrder getClosedSingleOrder(int id) {
		List<SingleOrder> ok = this.orderRepository.getClosedSingleOrders();
		for (int i = 0; i < ok.size(); i++) {
			if(ok.get(i).getId() == id)
				return ok.get(i);
		}
		
		return null;
	}
}
