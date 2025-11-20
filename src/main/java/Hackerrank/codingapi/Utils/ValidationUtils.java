package Hackerrank.codingapi.Utils;

import jakarta.validation.ValidationException;

import java.util.Objects;

public class ValidationUtils {

    // Null check
    public static void validateNotNull(Object obj, String fieldName) {
        if (Objects.isNull(obj)) {
            throw new ValidationException(fieldName + " cannot be null");
        }
    }

    // String not blank
    public static void validateNotBlank(String value, String fieldName) {
        if (value == null || value.isBlank()) {
            throw new ValidationException(fieldName + " cannot be blank");
        }
    }

    // Positive number check
    public static void validatePositive(long value, String fieldName) {
        if (value <= 0) {
            throw new ValidationException(fieldName + " must be positive");
        }
    }
}
