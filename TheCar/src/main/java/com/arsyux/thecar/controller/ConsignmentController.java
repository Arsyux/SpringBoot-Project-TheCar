package com.arsyux.thecar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ConsignmentController {
	
	// 탁송 예약 GetMapping
	@GetMapping("/consignment/reservationConsignment")
	public String reservationConsignment() {
		return "consignment/reservationConsignment";
	}
	
	// 탁송 예약 PostMapping
	//@PostMapping("/consignment/reservationConsignment")
	
	
}
