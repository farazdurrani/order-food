package com.restaurant.CSR;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.restaurant.CSR.ENTITY.Customer;
import com.restaurant.CSR.ENTITY.Menu;
import com.restaurant.CSR.ENTITY.MenuTypeEditor;
import com.restaurant.CSR.ENTITY.SingleOrder;
import com.restaurant.annotation.WebController;
import com.restaurant.sendemail.SendMail;

/*
 * I think I need to implement filters before showing menus. I need to implement that before going to menus and if user is not logged in, clear the old user's name.
 */

@WebController
@SessionAttributes("customer")
public class RestaurantController {

	//JUST TO HANDLE SINGLE ORDERS (ORDERS WITHOUT SIGNING UP). session ID is one way!
	private long sessionID(){
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		String sessionID = attr.getRequest().getSession().getId().replaceAll("[^\\d.]", "");
		if(sessionID.length() > 18){
			sessionID =  sessionID.substring(0, 17);
		}	
		long sessID = Long.parseLong(sessionID);
		return sessID;
	}
	@Autowired
	private MenuService menuService;

	@Autowired
	private SendMail sm;

	@ModelAttribute
	public Customer customerModal() {
		return new Customer();
	}
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Menu.class, new MenuTypeEditor());

	}

	@RequestMapping(value = { "", "list", "main", " " }, method = RequestMethod.GET)
	public String listMenus(Map<String, Object> model) {
		this.menuService.getAllMenusForShow();
		return "/list";
	}

	/*
	 * One thing that is happening is I don't have a check that once a person
	 * has ordered for a certain day, I should check here, and if he has, I
	 * should either a) send him to different view that shows you have already
	 * ordered for that day. or b) send him to same menu.jsp but put a check
	 * there inside foreach that if menu's day is filled, don't show him a check
	 * box! Upon testing, option b is not working. You have to treat two cases
	 * here. a) For today's menu. b) And for other day's menu.
	 */
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public String menuPage(@ModelAttribute(value = "customer") Customer customer, Model model) {
		
		
		/* AFTER OVERHAULING, THIS CHECK BELOW GOT BYPASSED BECAUSE customer is never null (because of customerModel()). So I added this additional
		 * check of fullname != null, because this will certainly fail. And that will send it to else. Otherwise, it will keep on going to this method:
		 * isThisPersonTryingToCheckTheMenuForTodayAndHeHasAlreadyOrdered_HeIsNotLoggedIn(long id). And this method makes an hibernate call and I don't
		 * want that */
		if (customer != null && customer.getFullname() != null) {// Is there a user? if yes then proceed.
			// It also means that someone must have
			// ordered something for at least 1 day.
			// Because his name is present.
			if (customer.getUserid() != null && !customer.getUserid().isEmpty() ) { // means that user is //if (!customer.getUserid().isEmpty())
				// already logged
				// in... he has a
				// name + userid and
				// password
				if (this.menuService.IsThisPersonTryingToCheckForTodaysMenuAndHeHasAlreadyOrderedForToday(
						customer.getUserid())) {// ... and he wanna check
					// the menu for today
					// but he has already
					// made a order for
					// today
					List<Menu> thisDaysMenus = this.menuService.getMenuOfThisPersonFromToday(customer.getUserid());
					model.addAttribute("today", thisDaysMenus);
					model.addAttribute("other", this.notTodaysDayName());
					model.addAttribute("price", this.price(thisDaysMenus));
					return "/menualreadyordered";
				} else { // ... but if he wants to check the menu for today and
					// he has NOT made a order for today, show him
					// this...
					customer.setMenus(null); //there is a better explanation for this lol. Think!
					model.addAttribute("today", this.menuService.getTodaysMenu());
					model.addAttribute("other", this.notTodaysDayName());
					return "/menu";
				}
			} else { // means that user is not logged in (his userid is empty)
				// but he does have name and he has ordered something for at least 1 day!
				/*
				 * I think I need to see what menus this person with only a name
				 * has in record. I need two checks here again. a) he wanna
				 * check the menu for today but he has already made a order for
				 * today if not, b) he wants to check the menu for today and he
				 * has NOT made a order for today. How could (b) be possible.
				 * Meaning how could a User object with only name be created
				 * without him ordering first? For that not to happen, you must
				 * make sure that on menu.jsp, no one should be able to put in a
				 * name only without selecting any menu!
				 */


				/*
				 * NEED BIG WORK HERE CUZ ITS CHECKING FOR SINGLE ORDERS
				 */
				if (this.menuService.isThisPersonTryingToCheckTheMenuForTodayAndHeHasAlreadyOrdered_HeIsNotLoggedIn(this.sessionID())) {// ... and he wanna check
					// the menu for today
					// but he has already
					// made a customer for
					// today

					List<Menu> thisDaysMenus = this.menuService.getMenuOfThisPersonWhoIsNotLoggedInFromToday(this.sessionID());
					model.addAttribute("today", thisDaysMenus);
					model.addAttribute("other", this.notTodaysDayName());
					model.addAttribute("price", this.price(thisDaysMenus));
					model.addAttribute("name", this.menuService.getNameForTodayOrder(this.sessionID() ) );
					model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
					return "/menualreadyordered";
				} else { // ... but if he wants to check the menu for today and
					// he has NOT made a order for today, show him
					// this...
					model.addAttribute("today", this.menuService.getTodaysMenu());
					model.addAttribute("other", this.notTodaysDayName());
					model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
					return "/menu";
				}

			}
		} else { // meaning he justs wants to browse the menus without filling
			// in anything! if he fills in, at least a user will be
			// created with a name but ofc no userid and password which
			// will take us to the above condition and we have already
			// implemented that.
			model.addAttribute("today", this.menuService.getTodaysMenu());
			model.addAttribute("other", this.notTodaysDayName());
			model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
			return "/menu";
		}
	}

	@RequestMapping(value = "/menu/{day}", method = RequestMethod.GET)
	public String otherDaysMenuPage(@PathVariable String day, @ModelAttribute(value = "customer") Customer customer,
			Model model) {		
		if (customer != null && customer.getFullname() != null) { // Is there a user? if yes then proceed.
			// It also means that someone must have
			// customered something for at least 1 day.
			// Because his name is present.
			if (customer.getUserid() != null && !customer.getUserid().isEmpty()) { // but he IS logged in //if (!customer.getUserid().isEmpty())
				// and does have a
				// user id and
				// password
				if (this.menuService.isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered(day,
						customer.getUserid())) { // check if he is trying
					// to check the menu for
					// a day for which he
					// has already placed an
					// customer? if yes, then

					List<Menu> thisDaysMenus = this.menuService.getMenuOfThisPersonFrom(day, customer.getUserid());
					model.addAttribute("today", thisDaysMenus);
					model.addAttribute("other", this.otherDayNamesButThisOne(day));
					model.addAttribute("price", this.price(thisDaysMenus));
					return "/menualreadyordered"; // send him to this page and
					// tell him that you have
					// already placed customer for
					// that day
				} else { // meaning that he is trying to check a menu for a day
					// and he has not placed an customer for that day
					customer.setMenus(null);										
					List<Menu> thisDaysMenusForShow = this.menuService.getMenu(day);
					model.addAttribute("today", thisDaysMenusForShow );
					model.addAttribute("other", this.otherDayNamesButThisOne(day));
					return "/menu";	

				}

			} else { // meaning that he is not logged in. He only has a name and
				// no userid and password. and he has customered something for at least 1 day!
				/*
				 * I think I need to see what menus this person with only a name
				 * has in record. I need two checks here again. a) he wanna
				 * check the menu for today but has he already made a customer for
				 * today? if not, b) he wants to check the menu for today and he
				 * has NOT made a customer for today. How could (b) be possible.
				 * Meaning how could a User object with only name be created
				 * without him customering first? For that not to happen, you must
				 * make sure that on menu.jsp, no one should be able to create a
				 * User object with name only without selecting any menu!
				 */

				/*
				 * NEED BIG WORK HERE BECAUSE THIS CODE BELOW CHECKS FOR SINGLE customerS.
				 */
				if (this.menuService.isThisPersonTryingToCheckTheMenuForWhichHeHasAlreadyOrdered_HeIsNotLoggedIn(day,
						this.sessionID())) { // check if he is trying
					// to check the menu for
					// a day for which he
					// has already placed an
					// customer? if yes, then
					List<Menu> thisDaysMenus = this.menuService.getMenuOfThisPersonWhoIsNotLoggedInFrom(day,
							this.sessionID());
					model.addAttribute("today", thisDaysMenus);
					model.addAttribute("other", this.otherDayNamesButThisOne(day));
					model.addAttribute("price", this.price(thisDaysMenus));
					model.addAttribute("name", this.menuService.getNameForThatDaysOrder( day, this.sessionID() ) );
					model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
					return "/menualreadyordered"; // send him to this page and
					// tell him that you have
					// already placed customer for
					// that day
				} else { // meaning that he is trying to check a meny for a day
					// and he has not placed an customer for that day
					model.addAttribute("today", this.menuService.getMenu(day));
					model.addAttribute("other", this.otherDayNamesButThisOne(day));
					model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
					return "/menu";
				}

			}
		} else { // meaning he justs wants to browse the menus without filling
			// in anything! if he fills in, at least a user will be
			// created with a name but ofc no userid and password. that
			// which will take us to the above condition and we have
			// already implemented that
			model.addAttribute("today", this.menuService.getMenu(day));
			model.addAttribute("other", this.otherDayNamesButThisOne(day));
			model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID() ) );
			return "/menu";
		}

	}

	@RequestMapping(value = "/ordersubmitted", method = RequestMethod.POST)
	public String done(@ModelAttribute(value = "customer") Customer customer, HttpServletRequest request, Model model) {

		String signUp = request.getParameter("signup");
		if (signUp != null) { // It means that he checked the signup button and filled his userid and password

			customer.setSignedin(true);

			List<SingleOrder> so = this.menuService.getRecordOfThisPersonWhoIsNotLoggedInFrom(this.sessionID());

			this.menuService.convertAndSaveSingleOrderToCustomer(so, customer);
			this.menuService.removeSingleOrder(this.sessionID());

			this.menuService.saveUser(customer);


		} else { // It means he did not check signup box or he signed up first or
			// signed in first (signing up will automatically sign in this person.
			// And a person who is signed in will not even see this 'sing up' checkbox.
			// thats why signup is null.) So he ordered something on menu.jsp page 
			// and now he is sent to this ordersubmitted.jsp page
			/*
			 * Here I have to check for 2 conditions. 1st) He intentionally does not want to sign up.
			 * Meaning that he wants to place customer without signing up. It means that he has no username (userid).
			 * 2nd) He already has a userid (because he signed in or signed up).
			 */

			if (customer.getUserid() != null && !customer.getUserid().isEmpty()) { // this meets 2nd condition. He has a userid (he logged in or signed up) //if (!customer.getUserid().isEmpty())
				this.menuService.saveUser(customer);
				sm.sendEmail(customer);
				model.addAttribute("alreadySignedUp", true);
			} else { //This meets the 1st condition. He wants to place Order without signing up. He has no username (userid).
				this.menuService.saveSingleOrder(new SingleOrder(this.sessionID(), customer.getFullname(), customer.getMenus())); 
				model.addAttribute("cartsize", this.menuService.getCartSize(this.sessionID()));
			}
		}
		List<Menu> m = customer.getMenus(); // just to satify this.price() method.
		// not gonna make another price
		// method. F no
		model.addAttribute("price", this.price(m));
		model.addAttribute("customer", customer);
		model.addAttribute("other", this.allDayNames());
		
		return "/ordersubmitted";
	}

	@RequestMapping(value = "/signupcomplete", method = RequestMethod.POST)
	public String signUpComplete(@ModelAttribute(value = "customer") Customer customer, HttpServletRequest request,
			Model model) {

		Customer o = new Customer(customer);


		String signUp = request.getParameter("signup");
		if (signUp != null) { // This is coming from the page where we show what
			// he customered for 'x' day and he decided to
			// finish signing up

			/* Here need to get all the orders in session that are already saved in database. 
			 * Get them, turn them into customer object 1 by 1, then save them 1 by 1, 
			 * then remove single orders of session. 
			 */
			List<SingleOrder> so = this.menuService.getRecordOfThisPersonWhoIsNotLoggedInFrom(this.sessionID());
			this.menuService.convertAndSaveSingleOrderToCustomer(so, o);
			this.menuService.removeSingleOrder(this.sessionID());
			

		} else { // He just straight up went to signup page and entered his
			// information. So naturally his menus are empty
			this.menuService.saveUser(o);
		}

		o.setSignedin(true);


		model.addAttribute("customer", o);  //I DON'T KNOW WHY I NEED TO ADD THIS CUSTOMER TO THE ATTRIBUTE WHEN ONE IS ALREADY PRESENT! NOW I FORGOT. SHOULD HAVE PENNED DOWN. 
		//TOO AFRAID TO CHANGE ANYTHING NOW JUST BEFORE DEPLOYING IT ON OPENSHIFT. WILL SEE LATER 
		return "/signupcomplete";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, SessionStatus sessionStatus, Model model) {
		request.getSession().invalidate();
		sessionStatus.setComplete(); // WHATEVER
		if (model.containsAttribute("Customer")) {
			model.asMap().remove("Customer");
		}
		return "redirect:/menu";

	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signUp(Model model, HttpSession session) {
	
		return "/signup";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signIn(Model model, HttpSession session) {
		return "/signin";
	}

	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signIn(@ModelAttribute(value = "customer") Customer customer, Model model, RedirectAttributes ra) {

		
		Customer o = new Customer(customer);
		String userid = o.getUserid();
		String password = o.getPassword();


		Customer u = this.menuService.getCustomerFromDatabase(userid, password);

		if (u != null) {	
			customer.setId(u.getId());
			customer.setFullname(u.getFullname());
			customer.setSignedin(true);
			//u.setSignedin(true);

			ra.asMap().clear(); //I DON'T REMEMBER WHY I ADDED THIS. THERE WAS A SOLID REASON FOR THIS. NOW I FORGOT. SHOULD HAVE PENNED DOWN THE REASON!

			return "redirect:/youraccount";  //THE PROBLEM IS IT WILL SEND HIM TO .GET /YOURACCOUNT.
		} else {		
			customer.setUserid("");
			customer.setPassword("");
			model.addAttribute("loginerror", "userid and/or password is incorrect. Try again ");
			return "/signin";
		}
	}

	@RequestMapping(value = "/youraccount", method = {RequestMethod.GET, RequestMethod.POST})
	public String yourAccountGet(@ModelAttribute(value = "customer") Customer customer, Model model, RedirectAttributes ra) {

		Customer o = new Customer(customer);
		String userid = o.getUserid();

		Set<String> history = this.menuService.getDaysOfOrdersBy(userid);
		model.addAttribute("history", history);
		return "/youraccount";

	}  

	@RequestMapping(value = "/fake", method = RequestMethod.GET)
	public String fakeMethod(Model model) {

		return "error";

	}  

	@RequestMapping(value = "/orderhistory/{day}", method = {RequestMethod.GET, RequestMethod.POST})
	public String customerHistory(@PathVariable String day, @ModelAttribute(value = "customer") Customer customer, Model model) {

		List<Menu> thisDaysMenus = this.menuService.getMenuOfThisPersonFrom(day, customer.getUserid());
		model.addAttribute("today", thisDaysMenus);
		model.addAttribute("price", this.price(thisDaysMenus));

		return "/orderhistory";
	}
	
	@RequestMapping(value = "/killsession", method = RequestMethod.GET)
	public String killSession(HttpServletRequest request, SessionStatus sessionStatus, Model model) {
		request.getSession().invalidate();
		sessionStatus.setComplete(); // WHATEVER
		if (model.containsAttribute("Customer")) {
			model.asMap().remove("Customer");
		}
		return "redirect:/menu";
	}

	private List<String> notTodaysDayName() {
		List<String> list1 = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday");
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equalsIgnoreCase(this.today())) {
				list2.add(list1.get(i));
			}
		}
		return list2;
	}

	private List<String> otherDayNamesButThisOne(String day) {
		List<String> list1 = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday");
		List<String> list2 = new ArrayList<String>();
		for (int i = 0; i < list1.size(); i++) {
			if (!list1.get(i).equalsIgnoreCase(day)) {
				list2.add(list1.get(i));
			}
		}
		return list2;
	}

	private List<String> allDayNames() {
		List<String> list1 = Arrays.asList("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday",
				"Sunday");
		return list1;
	}

	private String today() {
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String today = new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
		return today;
	}

	public String price(List<Menu> m) {
		if (m!=null){
			int charge = 0;
			for (int i = 0; i < m.size(); i++) {
				charge = charge + m.get(i).getPrice();
			}
			double tax = 0.085;
			double price = (charge * (tax)) + charge;

			return new DecimalFormat("##.##").format(price);
		} else {
			return "Error - can't calculate price... (you must order something first to see the price)";
		}

	}
}
