package com.test.tracking.util;

import java.util.regex.Pattern;

public class ValidationUtils {

	public static String validateInputs(String origin_country_id, String destination_country_id, String weight,
			String created_at, String customer_id, String customer_name, String customer_slug) {

		// Regex patterns for validation
		String isoAlpha2Pattern = "^[A-Z]{2}$";
		String weightPattern = "^\\d+(\\.\\d{1,3})?$";
		String createdAtPattern = "^\\d{4}-\\d{2}-\\d{2}T\\d{2}:\\d{2}:\\d{2}([+-]\\d{2}:\\d{2}|Z)$";
		String customerIdPattern = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
		String customerSlugPattern = "^[a-z0-9]+(?:-[a-z0-9]+)*$";

		if (!Pattern.matches(isoAlpha2Pattern, origin_country_id)) {
			return "Invalid origin country ID.";
		}

		if (!Pattern.matches(isoAlpha2Pattern, destination_country_id)) {
			return "Invalid destination country ID.";
		}

		if (!Pattern.matches(weightPattern, weight)) {
			return "Invalid weight.";
		}

		if (!Pattern.matches(createdAtPattern, created_at)) {
			return "Invalid created_at timestamp.";
		}

		if (!Pattern.matches(customerIdPattern, customer_id)) {
			return "Invalid customer_id.";
		}

		if (!Pattern.matches(".*[a-zA-Z].*", customer_name)) {
		    return "Invalid customer_name.";
		}

		if (!Pattern.matches(customerSlugPattern, customer_slug)) {
			return "Invalid customer_slug.";
		}

		return null; // No validation errors
	}

	public static String cleanCreatedAt(String createdAt) {
		if (createdAt.contains(" ") && createdAt.length() > 19) {
			createdAt = createdAt.replace(" ", "+");
		}
		if (createdAt.matches(".*\\d{2}:\\d{2}:\\d{2}\\d{2}:\\d{2}.*")) {
			createdAt = createdAt.substring(0, createdAt.length() - 5) + "+"
					+ createdAt.substring(createdAt.length() - 5);
		}
		return createdAt;
	}
}
