package com.royel.springdemo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.royel.springdemo.entity.Customer;
import com.royel.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// inject CustomerService into this controller
	@Autowired
	private CustomerService customerService;

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {
		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/list")
	public String listCustomer(Model model) {

		// get customers from dao
		List<Customer> theCustomers = customerService.getCustomers();

		// add customers to the model
		model.addAttribute("customers", theCustomers);

		return "list-customers";
	}

	@GetMapping("/addCustomer")
	public String addCustomerForm(Model model) {

		Customer customer = new Customer();
		model.addAttribute("customer", customer);

		return "add-customer-form";
	}

	@PostMapping("/saveCustomer")
	public String saveCustomer(@Valid @ModelAttribute("customer") Customer customer, BindingResult result) {

		if (result.hasErrors()) {
			return "add-customer-form";

		} else {
			customerService.saveCustomer(customer);
			return "redirect:/customer/list";
		}
	}

	@GetMapping("/update")
	public String customerUpdateForm(@RequestParam("customerId") int id, Model model) {

		Customer customer = customerService.getCustomer(id);

		model.addAttribute("customer", customer);

		return "add-customer-form";
	}

	@GetMapping("/delete")
	public String customerDelete(@RequestParam("customerId") int id) {

		customerService.deleteCustomer(id);

		return "redirect:/customer/list";
	}

}
