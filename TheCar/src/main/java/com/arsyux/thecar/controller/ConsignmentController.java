package com.arsyux.thecar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsignmentController {
	
	@GetMapping("/consignment/reservationConsignment")
	public String reservationConsignment() {
		return "consignment/reservationConsignment";
	}
	
}
