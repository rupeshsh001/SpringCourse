package com.testspring.spring.controller;

import java.util.HashMap;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.testspring.spring.constant.indexData;

@RestController
@RequestMapping(value = {""})
public class IndexInfo {
    @RequestMapping (value = {"/"}, method = RequestMethod.GET)
    public HashMap<String, Object> index() {
        HashMap<String, Object> obj = new HashMap<>();
        obj.put("developerName", indexData.devName);
        obj.put("projectName", indexData.projName);
        obj.put("version", 1.0);
        return obj;
    }
}
