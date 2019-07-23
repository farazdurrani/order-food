package com.restaurant.CSR;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.CSR.ENTITY.Admin;
import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.Menu;
import com.restaurant.CSR.ENTITY.MenuForShow;
import com.restaurant.CSR.ENTITY.SingleOrder;

@Repository
public class DefaultOrderRepository implements OrderRepository
{   
	@Autowired
	private SessionFactory sessionFactory;
    
	public DefaultOrderRepository(){    	
    }

    public DefaultOrderRepository(SessionFactory sessionFactory) {
    	this.sessionFactory = sessionFactory;
	}
	
		@Override
		@Transactional
		public long saveUser(Customer customer) {
			return (long) this.sessionFactory.getCurrentSession().save(customer);
		}

	   	@Override
	   	@Transactional
	   	public List<MenuForShow> getAllMenusForShow() { //LIKE WORKS LIKE A CHARM AS OF NOW!
	    	@SuppressWarnings("unchecked")
	    	List<MenuForShow> menusForShow = (List<MenuForShow>) sessionFactory.getCurrentSession().createSQLQuery("SELECT * FROM menusforshow").addEntity(MenuForShow.class).list();
	   		
	    	return menusForShow;
	   	}

	    @Override
		@Transactional
		public void saveSingleOrder(SingleOrder so) { //WORKS LIKE A CHARM! 
	    	this.sessionFactory.getCurrentSession().save(so);
		}
		@Override
		@Transactional
		public void removeSingleOrder(long id) { //WORKS LIKE A CHARM
			Query query = sessionFactory.getCurrentSession().createSQLQuery("delete from menus where single_order_id = " + id);		
			query.executeUpdate();
			Query query2 = sessionFactory.getCurrentSession().createSQLQuery("delete from singleorders where single_order_id = " + id);	
			query2.executeUpdate();
		}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(long id) {
		
		Query hquery = sessionFactory.getCurrentSession().createQuery("FROM SingleOrder WHERE single_order_id = :single_order_id");
		hquery.setLong("single_order_id", id);
		List<SingleOrder> SingleOrderList = (List<SingleOrder>) hquery.list();
			ArrayList<Menu> m = new ArrayList<Menu>();
			for(int i = 0; i  < SingleOrderList.size(); i++){
				m.addAll(SingleOrderList.get(i).getMenus());
			}
		return m;
	}

	@Override
	@Transactional
	public Customer getUserFromDatabase(long id) {
		String hql = "from Customer where id=" + id;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);		
		@SuppressWarnings("unchecked")
		List<Customer> customer = (List<Customer>) query.list();
		
		
		if (customer != null && !customer.isEmpty()) {
			return customer.get(0);		
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String getNameForTodayOrder(long id) {
		
		Calendar cal =  Calendar.getInstance();
 		Date d = cal.getTime(); 
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString); //today's date
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		Query hquery = sessionFactory.getCurrentSession().createQuery("FROM SingleOrder WHERE single_order_id = :single_order_id");
		hquery.setLong("single_order_id", id);
		List<SingleOrder> SingleOrderList = (List<SingleOrder>) hquery.list();
			String personName = "";
			for (int i = 0; i < SingleOrderList.size(); i++) {
				if(SingleOrderList.get(i).getSingle_order_id() == id){      //THIS CHECK IS WRONG. AM I NOT ALREADY PULLING RECORDS ONLY OF THAT PARTICULAR SINGLE_ORDER_ID? IMPROVE IT SOME OTHER
					for (int j = 0; j < SingleOrderList.get(i).getMenus().size(); j++) {
						if (SingleOrderList.get(i).getMenus().get(j).getOrderDate().compareTo(d2)==0){
							personName = SingleOrderList.get(i).getPersonName();
						}
					}
				}
			}			
			return personName;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public String getNameForThatDaysOrder(String day, long id) {
		final int DAYS_IN_WEEK = 7;
		ArrayList<Date> weeksDates = new ArrayList<Date>(); //this list has the dates of all the next 7 days
    	for (int j = 0; j < DAYS_IN_WEEK; j++) {
    		Calendar cal =  Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_YEAR, j);
    		Date d = cal.getTime();  
    		weeksDates.add(d);      		
		}
    	
    	Date actualDay = null;		//This date variable will hold the actual date of the day that has been passed thru the method parameters
    	
    	for (int i = 0; i < weeksDates.size(); i++) {
    		String dayname = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(weeksDates.get(i));
    		if (dayname.contentEquals(day)){
    			actualDay = weeksDates.get(i);
    			break;
    		}
    		
		}	

 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(actualDay);   //converted that date to string in this specific pattern because the dates coming in from DB are in this pattern
 		Date d2 = null;                                 //that actual date is in this date d2 variable now in a pattern that the incoming menus' dates will be in
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
 		Query hquery = sessionFactory.getCurrentSession().createQuery("FROM SingleOrder WHERE single_order_id = :single_order_id");
		hquery.setLong("single_order_id", id);
		List<SingleOrder> SingleOrderList = (List<SingleOrder>) hquery.list();
			String personName = "";
			for (int i = 0; i < SingleOrderList.size(); i++) {
				if(SingleOrderList.get(i).getSingle_order_id() == id){      //THIS CHECK IS WRONG. AM I NOT ALREADY PULLING RECORDS ONLY OF THAT PARTICULAR SINGLE_ORDER_ID? IMPROVE IT SOME OTHER
					for (int j = 0; j < SingleOrderList.get(i).getMenus().size(); j++) {
						if (SingleOrderList.get(i).getMenus().get(j).getOrderDate().compareTo(d2)==0){
							personName = SingleOrderList.get(i).getPersonName();
						}
					}
				}
			}			
			return personName;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Menu> getAllMenusOfThisPerson(String userid) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from Customer where userid = :userid"); 
		hquery.setString("userid", userid);
		
		ArrayList<Customer> customer = (ArrayList<Customer>)hquery.list();
		
		ArrayList<Menu> m = new ArrayList<Menu>();
		for (int i = 0; i < customer.size(); i++) {
			m.addAll(customer.get(i).getMenus());
		}
		
		return m;
	}

	@Override
	@Transactional
	public List<SingleOrder> getRecordOfThisPersonWhoIsNotLoggedInFrom(long id) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("FROM SingleOrder WHERE single_order_id = :single_order_id");
		hquery.setLong("single_order_id", id);
		
		@SuppressWarnings("unchecked")
		List<SingleOrder> SingleOrderList = (List<SingleOrder>) hquery.list();
		return SingleOrderList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Customer getCustomerFromDatabase(String userid, String password) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from Customer where password = :password and userid = :userid"); 
		hquery.setString("userid", userid);
		hquery.setString("password", password);
		
		ArrayList<Customer> customer = (ArrayList<Customer>)hquery.list();
		Customer c = null;
		if (customer != null && !customer.isEmpty()){
		 c = customer.get(0);
		}
		return c;
	}

	
	/*#################################################################################################
	 * All Methods below belong to Administrator
	 *#################################################################################################
	 */
	
	@Override
	@Transactional
	public Admin authenticateAdmin(String username, String password) {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from Admin where password = :password and username = :username"); 
		hquery.setString("username", username);
		hquery.setParameter("password", password);
		
		Admin a = (Admin)hquery.uniqueResult();
		
		return a;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> getOpenOrders() {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from Customer c inner join fetch c.menus m where m.open= :open"); 
		hquery.setBoolean("open", true);
		
		ArrayList<Customer> customer = (ArrayList<Customer>)hquery.list();
		
		return customer;
	}

	@Override
	@Transactional
	public void setThisOrderClose(long id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from Customer where id = :id ");
		   q.setParameter("id", id);
		   Customer c = (Customer)q.list().get(0);
		      for (int j = 0; j < c.getMenus().size(); j++) {
		    	  c.getMenus().get(j).setOpen(false);
			}
		   
		   sessionFactory.getCurrentSession().update(c);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<SingleOrder> getOpenSingleOrders() {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from SingleOrder c inner join fetch c.menus m where m.open= :open"); 
		hquery.setBoolean("open", true);
		
		ArrayList<SingleOrder> singleorder = (ArrayList<SingleOrder>)hquery.list();
		
		return singleorder;
	}

	@Override
	@Transactional
	public void setThisSingleOrderClose(long id) {
		Query q = sessionFactory.getCurrentSession().createQuery("from SingleOrder where id = :id ");
		   q.setParameter("id", id);
		   SingleOrder so = (SingleOrder)q.list().get(0);
		      for (int j = 0; j < so.getMenus().size(); j++) {
		    	  so.getMenus().get(j).setOpen(false);
			}
		   
		   sessionFactory.getCurrentSession().update(so);
		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<SingleOrder> getClosedSingleOrders() {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from SingleOrder c inner join fetch c.menus m where m.open= :open"); 
		hquery.setBoolean("open", false);
		
		ArrayList<SingleOrder> singleorder = (ArrayList<SingleOrder>)hquery.list();
		
		return singleorder;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Customer> getClosedOrders() {
		Query hquery = sessionFactory.getCurrentSession().createQuery("from Customer c inner join fetch c.menus m where m.open= :open"); 
		hquery.setBoolean("open", false);
		
		ArrayList<Customer> customer = (ArrayList<Customer>)hquery.list();
		
		return customer;
	}

}
