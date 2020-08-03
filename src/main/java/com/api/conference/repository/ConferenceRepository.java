package com.api.conference.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.conference.model.Conference;

public interface ConferenceRepository extends JpaRepository<Conference, Long>{

	Conference findByName(String conferenceName);

	Conference findByNameAndTitle(String name,String title);

}
