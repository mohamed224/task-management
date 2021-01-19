package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.enums.Status;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status status) {
        return status.getShortName();
    }

    @Override
    public Status convertToEntityAttribute(String shortName) {
        return Stream.of(Status.values()).filter(s -> s.getShortName().equals(shortName)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
