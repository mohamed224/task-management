package com.owntech.taskmanagement.converter;

import com.owntech.taskmanagement.enums.Priority;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class PriorityConverter implements AttributeConverter<Priority, String> {
    @Override
    public String convertToDatabaseColumn(Priority priority) {
        return priority.getShortName();
    }

    @Override
    public Priority convertToEntityAttribute(String shortName) {
        return Stream.of(Priority.values()).filter(p -> p.getShortName().equals(shortName)).findFirst().orElseThrow(IllegalArgumentException::new);
    }
}
