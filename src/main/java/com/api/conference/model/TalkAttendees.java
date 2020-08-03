package com.api.conference.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TALK_ATTENDEES")
public class TalkAttendees extends CommonFields{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long talkAttendeeId;

	@ManyToOne
	@JoinColumn(name = "talkId")
	private Talk talk;

	@NotNull
	private Integer attendeeType;

	@ManyToOne
	@JoinColumn(name = "employeeId")
	private Employee employee;
	
	@Column
	private Long conferenceId;
}
