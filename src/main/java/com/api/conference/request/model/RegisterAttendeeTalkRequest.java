package com.api.conference.request.model;

import javax.validation.constraints.NotNull;

import com.api.conference.model.AttendeeType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "employeeId", "talkId", "attendeeType" })
public class RegisterAttendeeTalkRequest {

	@NotNull
	@JsonProperty("employeeId")
	private Long employeeId;
	@NotNull
	@JsonProperty("talkId")
	private Long talkId;
	@NotNull
	@JsonProperty("attendeeType")
	private AttendeeType attendeeType;

}
