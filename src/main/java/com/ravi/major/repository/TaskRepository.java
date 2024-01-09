package com.ravi.major.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravi.major.entity.TaskAssign;

@Repository
public interface TaskRepository extends JpaRepository<TaskAssign, Long> {

	List<TaskAssign> findAllTaskAssignByEmployee_Id(int employeeId);

}
