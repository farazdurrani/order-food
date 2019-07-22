package com.restaurant.CSR;

import java.text.DecimalFormat;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.restaurant.CSR.ENTITY.Admin;
import com.restaurant.CSR.ENTITY.Menu;
import com.restaurant.annotation.WebController;

@WebController
@RequestMapping(value = {"/a"})
public class AdministratorController {
	
	@Autowired
	AdminMenuService adminMenuService;
	
	@RequestMapping(value = {}, method = RequestMethod.GET)
	public ModelAndView page0() {		
		return new ModelAndView("redirect:/a/signin");
	}
	
	@RequestMapping(value = {"/signin"}, method = RequestMethod.GET)
	public ModelAndView signin() {
		
			ModelAndView mav = new ModelAndView("AdminSignInPage");
			mav.addObject("welcome", "Admin Page");
			mav.addObject("admin", new Admin());
			return mav;
			
			/*return new ModelAndView("redirect:/a/signin");*/
		}
	
	@RequestMapping(value = {"/signin"}, method = RequestMethod.POST)
	public ModelAndView enteredCredentials(Admin admin) {
		
		Admin a = this.adminMenuService.authenticateAdmin(admin.getUsername(), admin.getPassword());
		

		if (a != null) {	
			ModelAndView mav = new ModelAndView("AdminYourAccount");
			mav.addObject("welcome", "Admin Page");
			mav.addObject("orders", this.adminMenuService.getOpenOrders());
			mav.addObject("singleorders", this.adminMenuService.getOpenSingleOrders());
			mav.addObject("closedorders", this.adminMenuService.getClosedOrders());
			mav.addObject("closedsingleorders", this.adminMenuService.getClosedSingleOrders());
			return mav;
		}
		else{
			return new ModelAndView("redirect:/a/signin");
		}
		
			
		}
	
	@RequestMapping(value = {"/reciept/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView reciept(@PathVariable int id) {			
		
			ModelAndView mav = new ModelAndView("reciept");
			mav.addObject("welcome", "Admin Page");
			mav.addObject("currentorder", this.adminMenuService.getOpenOrder(id));
			mav.addObject("price", this.price(this.adminMenuService.getOpenOrder(id).getMenus()));
			return mav;
		
		
			
		}
	
	@RequestMapping(value = {"/markedascompleted"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView done(HttpServletRequest request) {			
		
		this.adminMenuService.setThisOrderClosed(Long.parseLong(request.getParameter("id")));
		
		ModelAndView mav = new ModelAndView("AdminYourAccount");
		mav.addObject("welcome", "Admin Page");
		mav.addObject("orders", this.adminMenuService.getOpenOrders());
		mav.addObject("singleorders", this.adminMenuService.getOpenSingleOrders());
		mav.addObject("closedorders", this.adminMenuService.getClosedOrders());
		mav.addObject("closedsingleorders", this.adminMenuService.getClosedSingleOrders());
		return mav;
			
		}
	
	@RequestMapping(value = {"/recieptofsingleorder/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView recieptofsingleorder(@PathVariable int id) {			
		
		ModelAndView mav = new ModelAndView("recieptforsingleorder");
		mav.addObject("welcome", "Admin Page");
		mav.addObject("currentorder", this.adminMenuService.getOpenSingleOrder(id));
		mav.addObject("price", this.price(this.adminMenuService.getOpenSingleOrder(id).getMenus()));
		return mav;
		}
	
	@RequestMapping(value = {"/markedsingleorderascompleted"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView doneSingleOrder(HttpServletRequest request) {			
		
		this.adminMenuService.setThisSingleOrderClosed(Long.parseLong(request.getParameter("id")));
		
		ModelAndView mav = new ModelAndView("AdminYourAccount");
		mav.addObject("welcome", "Admin Page");
		mav.addObject("orders", this.adminMenuService.getOpenOrders());
		mav.addObject("singleorders", this.adminMenuService.getOpenSingleOrders());
		mav.addObject("closedorders", this.adminMenuService.getClosedOrders());
		mav.addObject("closedsingleorders", this.adminMenuService.getClosedSingleOrders());
		return mav;
			
		}
	
	@RequestMapping(value = {"/closedorderreciept/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView closedOrderReciept(@PathVariable int id) {			
		
			ModelAndView mav = new ModelAndView("closedorderreciept");
			mav.addObject("welcome", "Admin Page");
			mav.addObject("currentorder", this.adminMenuService.getClosedOrder(id));
			mav.addObject("price", this.price(this.adminMenuService.getClosedOrder(id).getMenus()));
			return mav;
		
		
			
		}
	
	@RequestMapping(value = {"/closedsingleorderreciept/{id}"}, method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView closedSingleOrderReciept(@PathVariable int id) {			
		
			ModelAndView mav = new ModelAndView("closedsingleorderreciept");
			mav.addObject("welcome", "Admin Page");
			mav.addObject("currentorder", this.adminMenuService.getClosedSingleOrder(id));
			mav.addObject("price", this.price(this.adminMenuService.getClosedSingleOrder(id).getMenus()));
			return mav;
		
		
			
		}
	
	
	private String price(List<Menu> menus) {
		double price = 0;
		for (int i = 0; i < menus.size(); i++) {
			price += menus.get(i).getPrice();
		}
		
		double tax = 0.085;
		price = (price * (tax)) + price;
		return new DecimalFormat("##.##").format(price);
	}
	
}
