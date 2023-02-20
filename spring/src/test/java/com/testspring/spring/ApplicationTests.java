package com.testspring.spring;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import static org.assertj.core.api.Assertions.assertThat;

// import all the static methods from the Assertions class, so we may use them in this class
import static org.junit.jupiter.api.Assertions.*;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.jayway.jsonpath.JsonPath;
import com.testspring.spring.controller.CourseController;
import com.testspring.spring.controller.IndexInfo;
import com.testspring.spring.controller.UserController;



@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private IndexInfo indexController;

	@Autowired
	private CourseController courseController;

	@Autowired
	private UserController userController;

	static String courseID;
	static String userID;


	@Test
	@Order(1)
	void checkIndexController() {
		assertThat(indexController).isNotNull();
	}

	@Test
	@Order(2)
	void checkCourseController() {
		assertThat(courseController).isNotNull();
	}

	@Test
	@Order(3)
	void checkUserController() {
		assertThat(userController).isNotNull();
	}

	String exampleCourseJson = "{\"title\": \"Testing Course\",\"durationInMonth\": \"1 month\",\"amount\": 399,\"languageRequired\": [	\"HTML/CSS\",	\"JAVASCRIPT\",	\"TYPESCRIPT\"],\"description\": \"this course course is added for testing purpose\"}";
	String exampleUserJson="{\"fullName\": \"Testing User\",\"email\": \"testing@gmail.com\",\"phone\": 7703968162,\"courses\": [],\"userType\": \"user\"}";
	@Test
	@Order(4)
	public void shouldReturnIndexJSON() throws Exception {
		mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().json("{'projectName':'Course','developerName':'Rupesh Sharma','version':1.0}",false));
	}

	@Test
	@Order(4)
	public void createCourse() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/course/addCourse")
				.accept(MediaType.APPLICATION_JSON).content(exampleCourseJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String stringifyResponse = result.getResponse().getContentAsString();
		courseID=JsonPath.parse(stringifyResponse).read("$.id");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		// exampleCourseJsonID = JsonPath.parse(stringifyResponse).read("$.id");
	}

	@Test
	@Order(5)
	void getAllCourse() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/course");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		System.out.println(result.getResponse().getContentAsString()+": get all Course response here");
	}

	@Test()
	@Order(5)
	void getCourseByTitle() throws Exception {
		RequestBuilder requestBuilderForID = MockMvcRequestBuilders
				.get("/course/search?searchby=Testing Course");
		MvcResult result = mockMvc.perform(requestBuilderForID).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test()
	@Order(6)
	void getCourseByID() throws Exception {
		RequestBuilder requestBuilderForID = MockMvcRequestBuilders
				.get("/course/" + courseID);
		MvcResult result = mockMvc.perform(requestBuilderForID).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String stringifyResponse = result.getResponse().getContentAsString();
		String idFromDB = JsonPath.parse(stringifyResponse).read("$.id");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(idFromDB, courseID);
	}

	@Test
	@Order(7)
	void deleteCourseByID() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/course/delete/" + courseID);
		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	@Order(8)
	public void createUser() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/addUser")
				.accept(MediaType.APPLICATION_JSON).content(exampleUserJson)
				.contentType(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String stringifyResponse = result.getResponse().getContentAsString();
		userID=JsonPath.parse(stringifyResponse).read("$.id");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		// exampleCourseJsonID = JsonPath.parse(stringifyResponse).read("$.id");
	}

	@Test
	@Order(9)
	void getAllUser() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user");
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		System.out.println(result.getResponse().getContentAsString()+": get all User response here");
	}

	@Test()
	@Order(10)
	void getUserByID() throws Exception {
		RequestBuilder requestBuilderForID = MockMvcRequestBuilders
				.get("/user/" + userID);
		MvcResult result = mockMvc.perform(requestBuilderForID).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String stringifyResponse = result.getResponse().getContentAsString();
		String idFromDB = JsonPath.parse(stringifyResponse).read("$.id");
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		assertEquals(idFromDB, userID);
	}

	@Test
	@Order(11)
	void deleteUserByID() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("/user/delete/" + userID);
		MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();
		MockHttpServletResponse response = result.getResponse();
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
}
