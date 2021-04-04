package com.greenparkingbook.desiredevparkingmap.validator;

import com.greenparkingbook.desiredevparkingmap.dto.ChargingPointDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ChargingPointDtoValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return ChargingPointDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "typeOfCurrent", "typeOfCurrent.empty", "type of current must not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "power", "power.empty");
        ChargingPointDto chargingPointDto = (ChargingPointDto) target;
//        if (chargingPointDto.getId() < 10L) {
//            errors.rejectValue("id", "too.small");
//        }
    }
}
