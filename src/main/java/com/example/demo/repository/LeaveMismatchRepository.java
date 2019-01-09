package com.example.demo.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.LeaveMismatch;

public interface LeaveMismatchRepository extends CrudRepository<LeaveMismatch, Integer>{
	
	@Query("select e from LeaveMismatch e where e.employeeId=:employeeId and e.weekBegin=:weekBegin")  
	List<LeaveMismatch> getLeaveMismatchByWeekBegin(@Param("employeeId")Integer employeeId,@Param("weekBegin") Date weekBegin);
      
}
