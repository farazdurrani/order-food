package com.restaurant.CSR;


import java.util.List;
import java.util.Set;

import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.Menu;
import com.restaurant.CSR.ENTITY.MenuForShow;
import com.restaurant.CSR.ENTITY.SingleOrder;

public interface MenuService
{
	
	List<MenuForShow> getAllMenusForShow();
    List<Menu> getMenu(String day);
    void saveMenu(Menu menu);
	List<Menu> getTodaysMenu();
	List<Menu> getAllDaysMenuButToday();
	long saveUser(Customer customer);
	void saveSingleOrder(SingleOrder so);
	void removeSingleOrder(long id);
	List<Menu> getAllMenusOfThisPerson(String userid);
	boolean isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered(String day, String userid);
	List<Menu> getMenuOfThisPersonFrom(String day, String userid);
	boolean IsThisPersonTryingToCheckForTodaysMenuAndHeHasAlreadyOrderedForToday(String userid);
	Customer getUserFromDatabase(long id);
	Set<String> getDaysOfOrdersBy(String userid);
	boolean isThisPersonTryingToCheckTheMenuForTodayAndHeHasAlreadyOrdered_HeIsNotLoggedIn(long id);
	List<Menu> getMenuOfThisPersonWhoIsNotLoggedInFrom(String day, long id);
	boolean isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered_HeIsNotLoggedIn(String day, long id);
	String getNameForThatDaysOrder(String today, long id);
	List<SingleOrder> getRecordOfThisPersonWhoIsNotLoggedInFrom(long id);
	void convertAndSaveSingleOrderToCustomer(List<SingleOrder> so, Customer o);
	Customer getCustomerFromDatabase(String userid, String password);
	List<Menu> getMenuOfThisPersonFromToday(String userid);
	List<Menu> getMenuOfThisPersonWhoIsNotLoggedInFromToday(long id);
	String getNameForTodayOrder(long id);
	int getCartSize(long sessionID);
	
}
