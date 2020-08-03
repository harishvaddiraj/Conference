package com.api.conference.model;

public enum AttendeeType {
	PRESENTER(1), ATTENDEE(2);
	
	private final int attendeeCode;

	AttendeeType(int code) {
		this.attendeeCode = code;
	}

	public int getAttendeeCode() {
		return attendeeCode;
	}

}
