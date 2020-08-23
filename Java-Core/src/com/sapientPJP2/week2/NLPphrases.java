package com.sapientPJP2.week2;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class NLPphrases {

	public void handleOperation() {
		
		
		
		Map<String,LocalDate> phrasesToDate = new HashMap<>();
		phrasesToDate.put("today",currDate);
		phrasesToDate.put("tomorrow",currDate.plusDays(1));
		phrasesToDate.put("day after tomorrow",currDate.plusDays(2));
		phrasesToDate.put("yesterday",currDate.minusDays(1));
		phrasesToDate.put("day before yesterday",currDate.minusDays(2));
		phrasesToDate.put("last week",currDate.minusWeeks(1));
		phrasesToDate.put("previous week",currDate.minusWeeks(1));
		phrasesToDate.put("next week",currDate.plusWeeks(1));
		phrasesToDate.put("next month ",currDate.plusMonths(1));
		phrasesToDate.put("next year",currDate.plusYears(1));
		phrasesToDate.put("last month",currDate.minusMonths(1));
		phrasesToDate.put("last year",currDate.minusYears(1));
		phrasesToDate.put("month after",currDate.plusMonths(1));
		phrasesToDate.put("month before",currDate.minusMonths(1));
		phrasesToDate.put("weeks from now");
		phrasesToDate.put(16, "days from now");
		phrasesToDate.put(17, "months from now");
		phrasesToDate.put(18, "years from now");
		phrasesToDate.put(19, "days earlier");
		phrasesToDate.put(20, "weeks earlier");
		phrasesToDate.put(21, "months earlier");
		phrasesToDate.put(22, "years earlier");
	
		
	}

}