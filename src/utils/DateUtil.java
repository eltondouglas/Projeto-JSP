package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateUtil {

    public static final String PATTERN = "yyyy-MM-dd";
    public static final String PATTERN_BR = "dd/MM/yyyy";

    public static LocalDate stringToDate(String date, String PATTERN){
    	if (date.trim().isEmpty()){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern(PATTERN);
        try {
            return LocalDate.parse(date, dtf);
        } catch (DateTimeParseException e){
            throw new IllegalArgumentException("VERIFIQUE OS DADOS PREENCHIDOS");
        }

    }

    public static String dateToString(LocalDate localDate){

        return localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
