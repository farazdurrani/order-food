package com.restaurant.CSR;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.Menu;
import com.restaurant.CSR.ENTITY.MenuForShow;
import com.restaurant.CSR.ENTITY.SingleOrder;
@Service
public class DefaultMenuService implements MenuService
{
	@Autowired OrderRepository orderRepository;
	

    @Override
    public List<Menu> getMenu(String day)
    {	
    	final int DAYS_IN_WEEK= 7;
    	List<MenuForShow> m = this.getAllMenusForShow(); 	
    	List<MenuForShow> l = new ArrayList<MenuForShow>();
    	
    	for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getDay().equals(day)){
				l.add(m.get(i));
			}
		}
    	List<Menu> i = new ArrayList<Menu>();
    	for (int j = 0; j < l.size(); j++) {
    		i.add(new Menu(l.get(j).getDay(),l.get(j).getName(),l.get(j).getPrice()));			
		}
    	
    	ArrayList<Date> weeksDates = new ArrayList<Date>();
    	for (int j = 0; j < DAYS_IN_WEEK; j++) {
    		Calendar cal =  Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_YEAR, j);
    		Date d = cal.getTime();  
    		weeksDates.add(d);      		
		}

    	for (int j = 0; j < weeksDates.size(); j++) {
			String dayName = new SimpleDateFormat("EEEE").format(weeksDates.get(j)); 
			for (int k = 0; k < i.size(); k++) {
				if(i.get(k).getDay().contentEquals(dayName)){
					i.get(k).setOrderDate(weeksDates.get(j));
				}
				
			}
		}

		return i;
    }

    @Override
    public void saveMenu(Menu menu)
    {
        
    }
    
	@Override
	public List<Menu> getTodaysMenu() {
		
		ArrayList<Menu> m = (ArrayList<Menu>) this.getMenu(today());
		/*System.err.println("Inside getTodaysMenu()");
		for(int i = 0 ; i < m.size(); i++){
			m.get(i).setOrderDate(new Date());
			System.err.println(m.get(i));
		}
		System.err.println("==========================");*/
		return m;

	}

	public String today(){
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		return today;
	}

	@Override
	public List<Menu> getAllDaysMenuButToday() {
		List<Menu> m1= new ArrayList<Menu>();
		List<Menu> m2 = new ArrayList<Menu>();
		
		List<MenuForShow> m = this.getAllMenusForShow(); 	
    	List<Menu> l = new ArrayList<Menu>();

  
    	for (int j = 0; j < m.size(); j++) {
    		l.add(new Menu(m.get(j).getDay(),m.get(j).getName(),m.get(j).getPrice()));			
		}
		
		
		
		m1.addAll(l); //contains everyday's menues
		m2.addAll(this.getTodaysMenu()); //contains today's menues
		
		List<Menu> union = new ArrayList<Menu>(m1);
		union.addAll(m2);
		// Prepare an intersection
		List<Menu> intersection = new ArrayList<Menu>(m1);
		intersection.retainAll(m2);
		// Subtract the intersection from the union
		union.removeAll(intersection);
		// Print the result

		

		return union;
	}

	@Override
	public long saveUser(Customer customer) {
		long id = this.orderRepository.saveUser(customer);
		System.err.println("THIS USER IS SAVED WITH ID: " + id);
		return id;
		
	}

	@Override
	public void saveSingleOrder(SingleOrder so) {
				
		this.orderRepository.saveSingleOrder(so);
		
	}
	

	@Override
	public void removeSingleOrder(long id) {
		 this.orderRepository.removeSingleOrder(id);
		
	}
	
	@Override
	public List<Menu> getAllMenusOfThisPerson(String userid) {
		return this.orderRepository.getAllMenusOfThisPerson(userid);
		
	}

	@Override
	public boolean isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered(String day, String userid) {
		final int DAYS_IN_WEEK= 7;
		ArrayList<Date> weeksDates = new ArrayList<Date>();
    	for (int j = 0; j < DAYS_IN_WEEK; j++) {
    		Calendar cal =  Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_YEAR, j);
    		Date d = cal.getTime();  
    		weeksDates.add(d);      		
		}
    	Date d = null; //this date will hold the day's date. 
    	for (int i = 0; i < weeksDates.size(); i++) {
			String dayName = new SimpleDateFormat("EEEE").format(weeksDates.get(i));
			if(day.contentEquals(dayName)){
				d = weeksDates.get(i);
				break;
			}
		}
    	
    	Set<Date> s = new HashSet<Date>();
		List<Menu> m = this.orderRepository.getAllMenusOfThisPerson(userid);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getOrderDate());
			}
		System.err.println("Set<Date> s has: " + s); 
		
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 if(s.contains(d2)){
				return true;
		 }		
		
		return false;
/*		Set<String> s = new HashSet<String>();
		List<Menu> m = new ArrayList<Menu>();
		 m = this.orderRepository.getAllMenusOfThisPerson(userid);
		 for (int i = 0; i < m.size(); i++) {
			s.add(m.get(i).getDay());
		}
		if(s.contains(day))
			return true;
		return false;*/
	}
	
	@Override
	public List<Menu> getMenuOfThisPersonFromToday(String userid) {
		
		List<Menu> m = this.orderRepository.getAllMenusOfThisPerson(userid);
		
		List<Menu> n = new ArrayList<Menu>();
		
		Calendar cal =  Calendar.getInstance();
 		Date d = cal.getTime(); 
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		System.err.println("Inside getMenuOfThisPersonFrom(String day, String userid), d2 is: " + d2);
 		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getOrderDate().compareTo(d2)==0){
				System.err.println("found it! " + m.get(i).getOrderDate() );
				n.add(m.get(i));
			}
		}
		
		
		return n;
		/*List<Menu> m = new ArrayList<Menu>();
		List<Menu> n = new ArrayList<Menu>();
		m = this.orderRepository.getAllMenusOfThisPerson(userid);
		
		
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getDay().equalsIgnoreCase(day)){
				n.add(m.get(i));
			}
		}
		return n;*/
	}


	@Override
	public List<Menu> getMenuOfThisPersonFrom(String day, String userid) {
		System.err.println("I, getMenuOfThisPersonFrom(String day, String userid), am called");
		System.err.println("################################################################");
		final int DAYS_IN_WEEK = 7;
		
		List<Menu> m = this.orderRepository.getAllMenusOfThisPerson(userid); //this list has all the orders of this person with this unique userid (email)
		System.err.println("this.orderRepository.getAllMenusOfThisPerson(userid) gets me: " + m);
		
		List<Menu> n = new ArrayList<Menu>();	// this list has all the orders of that specific day of his person with this unique userid (email)
		
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getDay().equalsIgnoreCase(day)){
				n.add(m.get(i));
			}
		}
		
		System.err.println("This list has all the menus of this " + day + ":" + n);
		
		ArrayList<Date> weeksDates = new ArrayList<Date>(); //this list has the dates of all the next 7 days
    	for (int j = 0; j < DAYS_IN_WEEK; j++) {
    		Calendar cal =  Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_YEAR, j);
    		Date d = cal.getTime();  
    		weeksDates.add(d);      		
		}
    	
    	System.err.println("Next 7 dats Dates are " + weeksDates);
    	
    	Date actualDay = null;		//This date variable will hold the actual date of the day that has been passed thru the method parameters
    	
    	for (int i = 0; i < weeksDates.size(); i++) {
    		String dayname = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(weeksDates.get(i));
    		if (dayname.contentEquals(day)){
    			actualDay = weeksDates.get(i);
    			break;
    		}
    		
		}	
    	
    	System.err.println("This is the date we looking for " + actualDay);
    	
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(actualDay);   //converted that date to string in this specific pattern because the dates coming in from DB are in this pattern
 		Date d2 = null;                                 //that actual date is in this date d2 variable now in a pattern that the incoming menus' dates will be in
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		
 		System.err.println("Same date is a different pattern " + d2);
 		
 		List<Menu> o = new ArrayList<Menu>();           //this list holds the actual menus that are needed to be displayed
 		
 		for (int i = 0; i < n.size(); i++) {
			if(n.get(i).getOrderDate().compareTo(d2)==0){				
				o.add(n.get(i));
			}
		}
 		
 		System.err.println("This list has all the orders of this " + d2 + " date" + o);
 		
 		System.err.println("################################################################");
		
		return o;
		
	}

	@Override
	public boolean IsThisPersonTryingToCheckForTodaysMenuAndHeHasAlreadyOrderedForToday(String userid) {
		
		Set<Date> s = new HashSet<Date>();
		List<Menu> m = this.orderRepository.getAllMenusOfThisPerson(userid);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getOrderDate());
			}
		System.err.println("Set<Date> s has: " + s); 
		Calendar cal =  Calendar.getInstance();
 		Date d = cal.getTime(); 
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 if(s.contains(d2)){
				return true;
		 }		
				
		return false;
		
		/*Set<String> s = new HashSet<String>();
		List<Menu> m = new ArrayList<Menu>();
		 m = this.orderRepository.getAllMenusOfThisPerson(userid);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getDay());
			}
		 if(s.contains(today()))
				return true;
		return false;*/
	}

	@Override
	public List<Menu> getMenuOfThisPersonWhoIsNotLoggedInFrom(String day, long id) {
		final int DAYS_IN_WEEK = 7;
		List<Menu> m =  this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		List<Menu> n = new ArrayList<Menu>();
		
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getDay().equalsIgnoreCase(day)){
				n.add(m.get(i));
			}
		}
				
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
 	 		
 		List<Menu> o = new ArrayList<Menu>();           //this list holds the actual menus that are needed to be displayed
 		
 		for (int i = 0; i < n.size(); i++) {
			if(n.get(i).getOrderDate().compareTo(d2)==0){				
				o.add(n.get(i));
			}
		}
		
		return o;
		
	}
	@Override
	public List<Menu> getMenuOfThisPersonWhoIsNotLoggedInFromToday(long id) {
		List<Menu> m =  this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		List<Menu> n = new ArrayList<Menu>();
		
		Calendar cal =  Calendar.getInstance();
 		Date d = cal.getTime(); 
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
 		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getOrderDate().compareTo(d2)==0){
				System.err.println("found it! " + m.get(i).getOrderDate() );
				n.add(m.get(i));
			}
		}
		
		
		return n;
	/*	List<Menu> m = new ArrayList<Menu>();
		List<Menu> n = new ArrayList<Menu>();
		m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		 System.err.println("MOFO DID YOU TRY TO CALL ME FROM List<Menu> getMenuOfThisPersonWhoIsNotLoggedInFrom ");
		for (int i = 0; i < m.size(); i++) {
			if(m.get(i).getDay().equalsIgnoreCase(day))
				n.add(m.get(i));
		}
		
		return n;*/
	}
	@Override
	public List<SingleOrder> getRecordOfThisPersonWhoIsNotLoggedInFrom(long id) {

		return this.orderRepository.getRecordOfThisPersonWhoIsNotLoggedInFrom(id);
	}
	
	@Override
	public boolean isThisPersonTryingToCheckTheMenuForTodayAndHeHasAlreadyOrdered_HeIsNotLoggedIn(long id) {
		
		Set<Date> s = new HashSet<Date>();
		List<Menu> m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getOrderDate());
			}
		System.err.println("Set<Date> s has: " + s); 
		Calendar cal =  Calendar.getInstance();
 		Date d = cal.getTime(); 
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 if(s.contains(d2)){
				return true;
		 }		
				
		return false;
		
		
		
		/*Set<String> s = new HashSet<String>();
		List<Menu> m = new ArrayList<Menu>();
		 m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		 System.err.println("MOFO DID YOU TRY TO CALL ME FROM boolean isThisPersonTryingToCheckTheMenuForTodayAndHeHasAlreadyOrdered_HeIsNotLoggedIn ");
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getDay());
			}
		 if(s.contains(today()))
				return true;
		return false;*/
	}

	@Override
	public Customer getUserFromDatabase(long id) {		
		return this.orderRepository.getUserFromDatabase(id);
	}

	@Override
	public Set<String> getDaysOfOrdersBy(String userid) {		
		Set<String> s = new HashSet<String>();
		List<Menu> l = this.getAllMenusOfThisPerson(userid);
		for (int i = 0; i < l.size(); i++) {
			s.add(l.get(i).getDay());
		}
		return s;
	}



	@Override
	public List<MenuForShow> getAllMenusForShow() {
		
		List<MenuForShow> l = this.orderRepository.getAllMenusForShow();

		return l;
	}


	@Override
	public boolean isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered_HeIsNotLoggedIn(String day, long id) {
		
		System.err.println("isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered_HeIsNotLoggedIn(String day, long id) is at your service where you expected");
		final int DAYS_IN_WEEK= 7;
		ArrayList<Date> weeksDates = new ArrayList<Date>();
    	for (int j = 0; j < DAYS_IN_WEEK; j++) {
    		Calendar cal =  Calendar.getInstance();
    		cal.add(Calendar.DAY_OF_YEAR, j);
    		Date d = cal.getTime();  
    		weeksDates.add(d);      		
		}
    	Date d = null; //this date will hold the day's date. 
    	for (int i = 0; i < weeksDates.size(); i++) {
			String dayName = new SimpleDateFormat("EEEE").format(weeksDates.get(i));
			if(day.contentEquals(dayName)){
				d = weeksDates.get(i);
				break;
			}
		}
    	
    	Set<Date> s = new HashSet<Date>();
		List<Menu> m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getOrderDate());
			}
		System.err.println("Set<Date> s has: " + s); 
		
 		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
 		String testDateString = df.format(d);
 		Date d2 = null;
 		try {
			d2 = df.parse(testDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		 if(s.contains(d2)){
				return true;
		 }		
		
		return false;
/*		Set<String> s = new HashSet<String>();
		List<Menu> m = new ArrayList<Menu>();
		 m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(id);
		 for (int i = 0; i < m.size(); i++) {
			s.add(m.get(i).getDay());
		}
		if(s.contains(day))
			return true;
		return false;*/
	}

	@Override
	public String getNameForTodayOrder(long id) {
		
		return this.orderRepository.getNameForTodayOrder(id);
	}

	@Override
	public String getNameForThatDaysOrder(String today, long id) {
		
		return this.orderRepository.getNameForThatDaysOrder(today,id);
	}
	@Override
	public void convertAndSaveSingleOrderToCustomer(List<SingleOrder> so, Customer o) {
		Customer c = null;
		for(int i = 0; i < so.size(); i++){
			c = new Customer(so.get(i).getPersonName(), o.getUserid(), o.getPassword(), so.get(i).getMenus());
			this.orderRepository.saveUser(c);
		}
		
	}

	@Override
	public Customer getCustomerFromDatabase(String userid, String password) {		
		return this.orderRepository.getCustomerFromDatabase(userid, password);
	}

	@Override
	public int getCartSize(long sessionID) {
		Set<String> s = new HashSet<String>();
		List<Menu> m = this.orderRepository.getAllMenusOfThisPersonFromSaveSingleOrdersDatabase(sessionID);
		 for (int i = 0; i < m.size(); i++) {
				s.add(m.get(i).getDay());
			}
		 System.err.println("getCartSize() is : s" + "\n" +s.size());
		 return s.size();
	}


   
}
