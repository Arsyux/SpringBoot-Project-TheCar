package com.arsyux.thecar.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RESTController {

	// GET: SELECT
	@GetMapping("/thecar")
	public String httpGET() {
		return "GET 요청 처리";
	}
	
	// POST: INSERT
	@GetMapping("/thecar")
	public String httpPOST() {
		return "POST 요청 처리";
	}
	
	// PUT: UPDATE
	@PutMapping("/thecar")
	public String httpPUT() {
		return "PUT 요청 처리";
	}
	
	// DELETE: DELETE
	@DeleteMapping("/thecar")
	public String httpDELETE() {
		return "DELETE 요청 처리";
	}
	
}
