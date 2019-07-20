package com.restaurant.sendemail;

import java.io.StringWriter;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.restaurant.CSR.ENTITY.Customer;


@Component
public class SendMail {
	
	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	
	public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
	
	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}
	
	
	
	public void Send(Customer student){
		SimpleMailMessage email = new SimpleMailMessage();
		email.setTo(student.getUserid());
		email.setSubject("XYZ Restaurant Order Confirmation");
		
		Template template = velocityEngine.getTemplate("templates/emailtemplate.vm");
		VelocityContext velocityContext = new VelocityContext();
		velocityContext.put("fullname", student.getFullname());		
		velocityContext.put("menus", student.getMenus());
		

		StringWriter stringWriter = new StringWriter();

		template.merge(velocityContext, stringWriter);

		email.setText(stringWriter.toString());

		mailSender.send(email);
	
	}

}
