package xlsx.service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class GeneralServices {
	
	static private LocalDateTime convertToLocalDateTime(Date dateToConvert) {
	    return dateToConvert.toInstant()
	      .atZone(ZoneId.systemDefault())
	      .toLocalDateTime();
	}
	
	static public String convertDateCellValueToDateString(Date originalDate) {
		LocalDateTime date = convertToLocalDateTime(originalDate);
		
		String day = Integer.toString(date.getDayOfMonth());
		day = date.getDayOfMonth() < 10 ? "0"+ day : day;
		
		String month = Integer.toString(date.getMonthValue());
		month = date.getMonthValue() < 10 ? "0"+ month : month;
		
		String year = Integer.toString(date.getYear());
		
		return day + "/" + month + "/" + year;
		
	}

}
