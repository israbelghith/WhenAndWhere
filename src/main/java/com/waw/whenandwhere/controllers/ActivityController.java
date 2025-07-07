package com.waw.whenandwhere.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.waw.whenandwhere.entities.Activity;
import com.waw.whenandwhere.services.ActivityService;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

    
    @GetMapping
    public List<Activity> getAllActivities() {
        return activityService.findAllActivities();
    }
}
