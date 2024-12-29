package com.authkey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unicore.customeResponse.ResponseEntityWrapper;

@RestController
public class EsecurityController {
	
	
	@GetMapping("/myCards")
	private final String card() {
		return "card";
	}
	
	@GetMapping("/notices")
	private final ResponseEntityWrapper<?> notices() {
		return new ResponseEntityWrapper<>("Success", "Operation completed successfully.");
	}
	
}
