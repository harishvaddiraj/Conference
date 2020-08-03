package com.api.conference;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
public class HttpRequestTest {

	public static final String REGISTER_ATTENDEE_CONFRENCE_REQ = "{\"attendeeType\":\"PRESENTER\",\"conferenceName\":\"Java\",\"conferenceTitle\":\"Java Discussion\",\"employeeId\":1,\"talkId\":1}";
	public static final String REGISTER_ATTENDEE_CONFRENCE_RES = "{\"talkAttendeeId\":3,\"talk\":{\"talkId\":1,\"conferenceId\":2,\"talkName\":\"DEFAULT\",\"talkTitle\":\"Normal Talk\",\"talkStartTime\":\"2020-07-30T02:30:00.000+0000\",\"talkEndTime\":\"2020-07-30T03:30:00.000+0000\"},\"attendeeType\":1,\"employee\":{\"employeeId\":1,\"employeeName\":\"Harish\"},\"conferenceId\":2}";

	public static final String REGISTER_ATTENDEE_TO_TALK_REQ = "{\"attendeeType\":\"PRESENTER\",\"employeeId\":2,\"talkId\":1}";
	public static final String REGISTER_ATTENDEE_TO_TALK_RES = "{\"talkAttendeeId\":4,\"talk\":{\"talkId\":1,\"conferenceId\":2,\"talkName\":\"DEFAULT\",\"talkTitle\":\"Normal Talk\",\"talkStartTime\":\"2020-07-30T02:30:00.000+0000\",\"talkEndTime\":\"2020-07-30T03:30:00.000+0000\"},\"attendeeType\":1,\"employee\":{\"employeeId\":2,\"employeeName\":\"Vaddiraju\"},\"conferenceId\":2}";

	public static final String TALK_ATTENDEES_RES = "[{\"employeeId\":1,\"employeeName\":\"Harish\"},{\"employeeId\":2,\"employeeName\":\"Vaddiraju\"}]";

	public static final String ATTENDEE_SCHEDULE_RES = "[{\"talkId\":1,\"conferenceId\":2,\"talkName\":\"DEFAULT\",\"talkTitle\":\"Normal Talk\",\"talkStartTime\":\"2020-07-30T02:30:00.000+0000\",\"talkEndTime\":\"2020-07-30T03:30:00.000+0000\"}]";

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	@Order(1)
	public void testRegisterAttendeeConference() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(REGISTER_ATTENDEE_CONFRENCE_REQ, headers);
		assertEquals(REGISTER_ATTENDEE_CONFRENCE_RES, (this.restTemplate
				.postForObject("http://localhost:" + port + "/api/registerAttendeeConference", entity, String.class)));
	}

	@Test
	@Order(2)
	public void testRegisterAttendeetoTalk() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		HttpEntity<String> entity = new HttpEntity<>(REGISTER_ATTENDEE_TO_TALK_REQ, headers);
		assertEquals(REGISTER_ATTENDEE_TO_TALK_RES, (this.restTemplate
				.postForObject("http://localhost:" + port + "/api/registerAttendeeToTalk", entity, String.class)));
	}

	@Test
	@Order(3)
	public void testTalkAttendees() throws Exception {
		assertEquals(TALK_ATTENDEES_RES, (this.restTemplate
				.getForObject("http://localhost:" + port + "api/talkAttendees?talkId=1", String.class)));
	}

	@Test
	@Order(4)
	public void testAttendeeSchedule() throws Exception {
		assertEquals(ATTENDEE_SCHEDULE_RES, (this.restTemplate
				.getForObject("http://localhost:" + port + "/api/attendeeSchedule?employeeId=1", String.class)));
	}
}