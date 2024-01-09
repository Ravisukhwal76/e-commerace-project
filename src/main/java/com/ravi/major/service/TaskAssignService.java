package com.ravi.major.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ravi.major.entity.TaskAssign;
import com.ravi.major.repository.TaskRepository;

@Controller
public class TaskAssignService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public List<TaskAssign> getTasks(){
        return taskRepository.findAll();
    }
    public void addTask(TaskAssign taskAssign){
    	taskRepository.save(taskAssign);
    }

    public void removeById(long id){
    	taskRepository.deleteById(id);
    }

    public Optional<TaskAssign> getTaskAssignById(long id){
        return taskRepository.findById(id);
    }

    public List<TaskAssign> getAllTaskAssignById(int id){
        return taskRepository.findAllTaskAssignByEmployee_Id(id);
    }
    public void deleteTaskAssignByemployeeId(int employeeId) {
        List<TaskAssign> taskAssignsToDelete = taskRepository.findAllTaskAssignByEmployee_Id(employeeId);

        for (TaskAssign taskAssign : taskAssignsToDelete) {
        	taskRepository.deleteById(taskAssign.getId());
        }
    }

}
