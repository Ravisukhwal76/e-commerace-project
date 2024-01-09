package com.ravi.major.controller;
 
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
 
 import com.ravi.major.dto.TaskAssignDto;
import com.ravi.major.entity.TaskAssign;
import com.ravi.major.service.EmployeeService;
import com.ravi.major.service.TaskAssignService;

@Controller
public class TaskController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private TaskAssignService taskAssignService;
	 
	
	@GetMapping("/task/add")
	public String tasks() {
		return "dashboard";
	} 
	
    @GetMapping("/dashboard")
    public String dashboard(){
       // model.addAttribute("cartCount", GlobalData.cart.size());
        return "dashboard";
    }
    
    
    @GetMapping("/assignTask")
    public String assignTask(Model model){
       // model.addAttribute("cartCount", GlobalData.cart.size());
    	 model.addAttribute("taskAssignDTO",new TaskAssignDto());
         model.addAttribute("employee",employeeService.getEmployee());
        return "TaskAssign";
    }
    
    @PostMapping("/task/assign/add")
    public String saveProduct(@ModelAttribute("taskAssignDTO")TaskAssignDto taskAssignDto
                              ) throws IOException {

      TaskAssign taskAssign = new TaskAssign();

 

      taskAssign.setDate(taskAssignDto.getDate());
      taskAssign.setDescription(taskAssignDto.getDescription());
      taskAssign.setEmployee(employeeService.getEmployeeById(taskAssignDto.getEmp_id()).get());
      taskAssign.setId(taskAssignDto.getId());
      taskAssign.setTitle(taskAssignDto.getTitle());
    
       
      
      taskAssignService.addTask(taskAssign);

        return "redirect:/task/add";
    }

}
