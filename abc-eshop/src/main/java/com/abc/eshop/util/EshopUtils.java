package com.abc.eshop.util;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class EshopUtils {

	public static String convertDateTimeToString(Date date, String dateFormat) {
		return date.toInstant()
				.atZone(ZoneId.of("CET"))
				.toLocalDateTime()
				.format(DateTimeFormatter.ofPattern(dateFormat));
	}
}
