package com.api.conference.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.conference.model.Talk;

public interface TalkRepository extends JpaRepository<Talk, Long>{

	Optional<Talk> findByTalkIdOrTalkName(Long talkId, String talkName);

}
