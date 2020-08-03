package com.api.conference.request.model;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "employeeId","conferenceName","conferenceTitle", "talkId","attendeeType" })
public class RegisterAttendeeRequest extends RegisterAttendeeTalkRequest{
	
	@NotNull
	@JsonProperty("conferenceName")
	private String conferenceName;
	@NotNull
	@JsonProperty("conferenceTitle")
	private String conferenceTitle;
	@JsonProperty("conferenceCompany")
	private String conferenceCompany;
	@JsonProperty("conferenceLocation")
	private String conferenceLocation;

}
