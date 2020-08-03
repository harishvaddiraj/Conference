package com.api.conference.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.conference.exception.ValidationException;
import com.api.conference.model.Employee;
import com.api.conference.model.Talk;
import com.api.conference.model.TalkAttendees;
import com.api.conference.request.model.RegisterAttendeeRequest;
import com.api.conference.request.model.RegisterAttendeeTalkRequest;
import com.api.conference.service.ConferenceService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api")
@Slf4j
public class ConferenceController {

	@Autowired
	private ConferenceService conferenceService;

	@PostMapping(value = "/registerAttendeeConference", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalkAttendees registerAttendee(@Valid @RequestBody RegisterAttendeeRequest registerAttendeeRequest) {
		log.info("Start: registerAttendee");
		return conferenceService.registerAttendee(registerAttendeeRequest);
	}

	@PostMapping(value = "/registerAttendeeToTalk", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public TalkAttendees registerAttendeeToTalk(
			@Valid @RequestBody RegisterAttendeeTalkRequest registerAttendeeTalkRequest) {
		log.info("Start: registerAttendeeToTalk");
		return conferenceService.registerAttendeetoTalk(registerAttendeeTalkRequest);
	}

	@GetMapping(value = "/talkAttendees", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Set<Employee> talkAttendees(@RequestParam(required=false) Long talkId, @RequestParam(required=false) String talkName) {
		log.info("Start: talkAttendees");
		if (Objects.isNull(talkId) && StringUtils.isEmpty(talkName)) {
			throw new ValidationException("talkId or talkName should be provided");
		}
		return conferenceService.getTalkAttendees(talkId, talkName);
	}
	
	@GetMapping(value = "/attendeeSchedule", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Talk> attendeeSchedule(@NotNull @RequestParam Long employeeId) {
		log.info("Start: attendeeSchedule");
		return conferenceService.getAttendeeSchedule(employeeId);
	}

}
