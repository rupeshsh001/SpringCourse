package com.testspring.spring.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.lang.Object;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.testspring.spring.entities.Course;

import main.java.com.testspring.spring.entities.AvailableCoruse;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;

import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.web.bind.annotation.PathVariable;
import com.fasterxml.jackson.databind.DeserializationFeature;
import org.json.simple.JSONObject;  
import org.json.simple.JSONValue;  




@RestController
@RequestMapping(value = {"/availablecourses"})
public class IndexInfo {
    @Bean
	public RestTemplate getRestTemplate() {
	   return new RestTemplate();
	}
    
    @RequestMapping(value = { "" }, method = RequestMethod.GET)
    public List<AvailableCoruse> getAllCourseRestTemp() {
        HttpHeaders headers = new HttpHeaders();
        List<AvailableCoruse> result = new ArrayList<>();
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ResponseEntity<Object[]> responseEntity = this.getRestTemplate()
                .getForEntity("http://localhost:8085/api/course", Object[].class);
                System.out.println(responseEntity+ "here the response");
        Object[] objects = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        for (Object e : objects) {
            Course ok_v = mapper.convertValue(e, Course.class);
            System.out.println(ok_v.getTitle());
            result.add(new AvailableCoruse(ok_v.getTitle(), ok_v.getAmount()));
        }
        
        return result;
    }
}
