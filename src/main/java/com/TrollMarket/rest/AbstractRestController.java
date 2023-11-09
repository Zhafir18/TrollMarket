package com.TrollMarket.rest;

import com.TrollMarket.dto.ValidationDTO;
import com.TrollMarket.utility.MapperHelper;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRestController {

    public List<ValidationDTO> getError(List<ObjectError> errors) {
        var dto = new ArrayList<ValidationDTO>();
        for (var error : errors) {
            var fieldName = MapperHelper.getStringField(error.getArguments()[0], "defaultMessage");
            fieldName = (fieldName.equals("")) ? "object" : fieldName;
            var message = error.getDefaultMessage();
            dto.add(new ValidationDTO(fieldName, message));
        }
        return dto;
    }
}
