package com.api.conference.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@Table(name = "CONFERENCE")
public class Conference extends CommonFields {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long conferenceId;
	@NotBlank
	private String name;
	@NotBlank
	private String title;
	private String company;
	private String location;
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "conferenceId")
	private Set<Talk> talks;

	public void addTalk(Talk talk) {
		if(Objects.isNull(talks)) {
			talks=new HashSet<>();
		}
		talks.add(talk);
	}
}
