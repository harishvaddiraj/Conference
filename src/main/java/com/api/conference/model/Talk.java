package com.api.conference.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "TALK")
public class Talk extends CommonFields {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long talkId;
	private Long conferenceId;
	@NotBlank
	private String talkName;
	@NotBlank
	private String talkTitle;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date talkStartTime;
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date talkEndTime;
	

}
