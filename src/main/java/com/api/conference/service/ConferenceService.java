package com.api.conference.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.api.conference.exception.ResourceNotFoundException;
import com.api.conference.exception.ValidationException;
import com.api.conference.model.Conference;
import com.api.conference.model.Employee;
import com.api.conference.model.Talk;
import com.api.conference.model.TalkAttendees;
import com.api.conference.repository.ConferenceRepository;
import com.api.conference.repository.EmployeeRepository;
import com.api.conference.repository.TalkAttendeesRepository;
import com.api.conference.repository.TalkRepository;
import com.api.conference.request.model.RegisterAttendeeRequest;
import com.api.conference.request.model.RegisterAttendeeTalkRequest;

@Service
public class ConferenceService {

	@Autowired
	private TalkRepository talkRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private ConferenceRepository conferenceRepository;

	@Autowired
	private TalkAttendeesRepository talkAttendeesRepository;

	public TalkAttendees registerAttendee(@Valid RegisterAttendeeRequest registerAttendeeRequest) {
		Optional<Talk> talk = talkRepository.findById(registerAttendeeRequest.getTalkId());
		Employee employee = employeeRepository.findById(registerAttendeeRequest.getEmployeeId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "id", registerAttendeeRequest.getEmployeeId()));
		Conference conference = conferenceRepository.findByNameAndTitle(registerAttendeeRequest.getConferenceName(),
				registerAttendeeRequest.getConferenceTitle());
		if (Objects.isNull(conference)) {
			conference = new Conference();
			conference.setName(registerAttendeeRequest.getConferenceName());
			conference.setTitle(registerAttendeeRequest.getConferenceTitle());
			conference.setCompany(registerAttendeeRequest.getConferenceCompany());
			conference.setLocation(registerAttendeeRequest.getConferenceLocation());
			conference.setCreatedTimestamp(new Date());
			conference.setUpdatedTimestamp(new Date());
		}
		if (talk.isPresent()) {
			conference.addTalk(talk.get());
		}

		conference = conferenceRepository.save(conference);

		TalkAttendees talkAttendees = new TalkAttendees();
		talkAttendees.setAttendeeType(registerAttendeeRequest.getAttendeeType().getAttendeeCode());
		talkAttendees.setEmployee(employee);
		if (talk.isPresent()) {
			Talk talk1 = talk.get();
			talk1.setConferenceId(conference.getConferenceId());
			talkAttendees.setTalk(talk1);
		}
		talkAttendees.setCreatedTimestamp(new Date());
		talkAttendees.setUpdatedTimestamp(new Date());
		talkAttendees.setConferenceId(conference.getConferenceId());

		return talkAttendeesRepository.save(talkAttendees);
	}

	public TalkAttendees registerAttendeetoTalk(@Valid RegisterAttendeeTalkRequest registerAttendeeTalkRequest) {
		Talk talk = talkRepository.findById(registerAttendeeTalkRequest.getTalkId()).orElseThrow(
				() -> new ResourceNotFoundException("Talk", "id", registerAttendeeTalkRequest.getTalkId()));
		Employee employee = employeeRepository.findById(registerAttendeeTalkRequest.getEmployeeId()).orElseThrow(
				() -> new ResourceNotFoundException("Employee", "id", registerAttendeeTalkRequest.getEmployeeId()));

		List<TalkAttendees> talkAttendeeList = talkAttendeesRepository.findAllBytalkAndEmployee(talk, employee);
		if (!CollectionUtils.isEmpty(talkAttendeeList)) {
			throw new ValidationException("Given employeeId" + registerAttendeeTalkRequest.getEmployeeId()
					+ " is already registered in the TalkId" + registerAttendeeTalkRequest.getTalkId());
		}

		TalkAttendees talkAttendees = new TalkAttendees();
		talkAttendees.setAttendeeType(registerAttendeeTalkRequest.getAttendeeType().getAttendeeCode());
		talkAttendees.setEmployee(employee);
		talkAttendees.setTalk(talk);
		if (Objects.nonNull(talk.getConferenceId())) {
			talkAttendees.setConferenceId(talk.getConferenceId());
		}
		talkAttendees.setCreatedTimestamp(new Date());
		talkAttendees.setUpdatedTimestamp(new Date());

		return talkAttendeesRepository.save(talkAttendees);
	}

	public Set<Employee> getTalkAttendees(Long talkId, String talkName) {
		Talk talk = talkRepository.findByTalkIdOrTalkName(talkId, talkName)
				.orElseThrow(() -> new ResourceNotFoundException("Talk", "id,Name", talkId + "," + talkName));
		List<TalkAttendees> talkAttendees = talkAttendeesRepository.findAllBytalk(talk);
		return talkAttendees.stream().map(ta -> ta.getEmployee()).collect(Collectors.toSet());
	}

	public List<Talk> getAttendeeSchedule(@NotNull Long employeeId) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Employee", "employeeId", employeeId));
		List<TalkAttendees> talkAttendees = talkAttendeesRepository.findAllByEmployee(employee);
		return talkAttendees.stream().map(ta -> ta.getTalk()).collect(Collectors.toList());
	}

}
