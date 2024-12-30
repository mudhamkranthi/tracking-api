package com.test.tracking.controller.service;

import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

@Service
public class TrackingNumberService {

	private static final ConcurrentHashMap<String, Boolean> generatedNumbers = new ConcurrentHashMap<>();
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int TRACKING_NUMBER_LENGTH = 16; // Maximum length for tracking number

	public String generateUniqueTrackingNumber(String origin_country_id, String destination_country_id, String weight,
			String created_at, String customer_id, String customer_name, String customer_slug) {
		String trackingNumber;

		do {
			trackingNumber = generateTrackingNumber(origin_country_id, destination_country_id, weight, created_at,
					customer_id, customer_name, customer_slug);
		} while (!isUnique(trackingNumber)); // Ensure uniqueness

		return trackingNumber;
	}

	private static String generateTrackingNumber(String origin_country_id, String destination_country_id, String weight,
			String created_at, String customer_id, String customer_name, String customer_slug) {
		StringBuilder trackingNumber = new StringBuilder();

		String parameters = origin_country_id + destination_country_id + weight + created_at + customer_id
				+ customer_name + customer_slug;

		String timestamp = new SimpleDateFormat("ddHHmmss").format(System.currentTimeMillis());
		long milliseconds = System.currentTimeMillis() % 1000;
		trackingNumber.append(timestamp).append(String.format("%03d", milliseconds));

		String paramHash = Integer.toHexString(parameters.hashCode()).toUpperCase();
		trackingNumber.append(paramHash);

		if (trackingNumber.length() > TRACKING_NUMBER_LENGTH) {
			trackingNumber.setLength(TRACKING_NUMBER_LENGTH);
		} else if (trackingNumber.length() < TRACKING_NUMBER_LENGTH) {
			String randomPart = generateRandomString(TRACKING_NUMBER_LENGTH - trackingNumber.length());
			trackingNumber.append(randomPart);
		}

		return trackingNumber.toString();
	}

	private static String generateRandomString(int length) {
		StringBuilder randomString = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(CHARACTERS.length());
			randomString.append(CHARACTERS.charAt(index));
		}
		return randomString.toString();
	}

	private static boolean isUnique(String trackingNumber) {
		return generatedNumbers.putIfAbsent(trackingNumber, Boolean.TRUE) == null;
	}
}
