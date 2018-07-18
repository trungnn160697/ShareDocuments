package com.rabiloo.sharedocument.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Format {

	public static String formatDate(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(date);
	}
}
