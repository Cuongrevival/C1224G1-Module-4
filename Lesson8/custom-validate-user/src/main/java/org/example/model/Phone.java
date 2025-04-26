package org.example.model;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;


@Component
public class Phone implements Validator {

    private String phoneNumber;

    public Phone() {
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Phone.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Phone phone = (Phone) target;
        String phoneNumber = phone.getPhoneNumber();
        ValidationUtils.rejectIfEmpty(errors, "phoneNumber", "phone.empty");
        if (phoneNumber.length() < 10 || phoneNumber.length() > 11)
            errors.rejectValue("phoneNumber", "phone.length");
        if (!phoneNumber.startsWith("0")) errors.rejectValue("phoneNumber", "phone.start");
        if (!phoneNumber.matches("($|[0-9]*$)")) errors.rejectValue("phoneNumber", "phone.digit");
    }
}
