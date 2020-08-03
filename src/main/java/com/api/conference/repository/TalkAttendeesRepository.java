package com.api.conference.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.conference.model.Employee;
import com.api.conference.model.Talk;
import com.api.conference.model.TalkAttendees;

public interface TalkAttendeesRepository extends JpaRepository<TalkAttendees, Long>{

	List<TalkAttendees> findAllBytalk(Talk talk);

	List<TalkAttendees> findAllByEmployee(Employee employee);
	
	List<TalkAttendees> findAllBytalkAndEmployee(Talk talk,Employee employee);

}
