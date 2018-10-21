package com.recruitment.util;

import org.apache.commons.validator.routines.EmailValidator;


public final class Validator {

    /*
     * Utility classes are helpers. They don't represent real world entity.
     * Hence Objects shouldn't be created and only static methods are allowed
     */
    private Validator() {
    }

    public static boolean validateEmail(final String email) {
        return EmailValidator.getInstance().isValid(email);
    }

}
