package com.test.tracking.controller;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.tracking.controller.service.TrackingNumberService;
import com.test.tracking.model.TrackingResponse;
import com.test.tracking.util.ValidationUtils;

@RestController
@RequestMapping("/tracking")
public class TrackingNumberController {

	@Autowired
	private TrackingNumberService trackingNumberService;

	@GetMapping("/next-tracking-number")
	public ResponseEntity<Object> getNextTrackingNumber(@RequestParam String origin_country_id,
			@RequestParam String destination_country_id, @RequestParam String weight, @RequestParam String created_at,
			@RequestParam String customer_id, @RequestParam String customer_name, @RequestParam String customer_slug) {

		created_at = ValidationUtils.cleanCreatedAt(created_at.trim());

		// Validate inputs
		String validationError = ValidationUtils.validateInputs(origin_country_id, destination_country_id, weight,
				created_at, customer_id, customer_name, customer_slug);
		if (validationError != null) {
			return ResponseEntity.badRequest().body(Map.of("error", validationError));
		}

		// Generate unique tracking number
		String trackingNumber = trackingNumberService.generateUniqueTrackingNumber();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssXXX");
		String formattedTimestamp = OffsetDateTime.now().format(formatter);

		TrackingResponse response = new TrackingResponse(trackingNumber, formattedTimestamp);

	    return ResponseEntity.ok(response);
	}
}
