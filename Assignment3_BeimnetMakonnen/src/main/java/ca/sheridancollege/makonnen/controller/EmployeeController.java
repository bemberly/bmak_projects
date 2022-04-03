package ca.sheridancollege.makonnen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.makonnen.beans.Employee;
import ca.sheridancollege.makonnen.database.DatabaseAccess;

@Controller
public class EmployeeController {
	
	@Autowired
	@Lazy
	DatabaseAccess da;	
	
	/* Processes HTTP request for Unsecured Index page*/
	@GetMapping("/")
	public String goIndex() {
		
		return "index";
	}
	
	/* Processes HTTP request for Login to Secure pages*/
	@GetMapping("/login")
	public String getLogin() {
		
		return "login";
	}
	
	/* Processes HTTP request for Logout*/
	@GetMapping("/logout")
	public String getLogout() {
		
		return "index";
	}
	
	/* Processes HTTP request for Secure Home page*/
	@GetMapping("/homePage")
	public String goHome() {
		
		da.updateNull();

		return "homePage";
	}
	
	/* Processes HTTP request for Permission-Denied page*/
	@GetMapping("/permission-denied")
	public String permissionDenied() {
		
		
		return "/error/permission-denied";
	}
	
	/* Processes HTTP request for Secure Insert Employee page*/
	@GetMapping("/insertEmployee")
	public String insertEmployee(Model model) {
		
		model.addAttribute("employee", new Employee());
	
		model.addAttribute("dList", da.getDepartmentIds());
		
		return "/insertEmployee";
	}
	
	/* Processes the Secure form submission for Insert Employee page*/
	@PostMapping("/processInsert")
	public String processInsert(Model model, @ModelAttribute Employee employee) {	
	
		da.insertEmployee(employee);
				
		model.addAttribute("employee", new Employee());
		
		model.addAttribute("dList", da.getDepartmentIds());
		
		return "/insertEmployee";
	}
	
	/* Processes HTTP request for Secure Update Employee page*/
	@GetMapping("/updateEmployee")
	public String updateEmployee(Model model) {
		
		model.addAttribute("employeeIds", da.getEmployeeIds());
		
		return "/updateEmployee";
	}
	
	/* Processes the Secure form submission for Update Employee page*/
	@PostMapping("/processUpdate")
	public String processUpdate(Model model, @RequestParam Long eId, @RequestParam double SALARY) {
		
		da.updateEmployee(eId, SALARY);
		
		model.addAttribute("employeeIds", da.getEmployeeIds());
		
		return "/updateEmployee";
	}
	
	/* Processes HTTP request for Secure Delete Employee page*/
	@GetMapping("/secure/deleteEmployee")
	public String deleteEmployee(Model model) {
		
		model.addAttribute("employeeIds", da.getEmployeeIds());
		
		return "/secure/deleteEmployee";
	}
	
	/* Processes the Secure form submission for Delete Employee page*/
	@PostMapping("/secure/processDelete")
	public String processDelete(Model model, @RequestParam Long eId) {
		
		da.deleteEmployee(eId);
		
		model.addAttribute("employeeIds", da.getEmployeeIds());
		
		return "/secure/deleteEmployee";
	}
	
	
}
