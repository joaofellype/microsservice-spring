package com.pagamento.pix.core.domain.shared.converters;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

public class DateConverter {

    public static LocalDateTime toLocalDateTime(Long dateLong){
       return LocalDateTime.ofInstant(Instant.ofEpochSecond(dateLong), TimeZone.getDefault().toZoneId());

    }
}
