package com.testspring.spring.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = {""})
public class IndexInfo {
    @RequestMapping (value = {"/"}, method = RequestMethod.GET)
    public HashMap<String, Object> index() {
        System.out.println("idhar aya h bhailoag");
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("developerName", "Rupesh Sharma");
        obj.put("projectName", "Course");
        obj.put("version", 1.0);
        return obj;
    }
}
