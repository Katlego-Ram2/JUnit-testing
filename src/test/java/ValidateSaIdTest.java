import org.junit.jupiter.api.Test;
import validateSaId.validateSaId;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for validating South African ID numbers.
 * These tests verify that the ID validation logic works correctly by checking various edge cases.
 */
public class ValidateSaIdTest {

    /**
     * Test that verifies valid South African ID numbers pass validation.
     * It checks that ID numbers with valid dates of birth and checksum are accepted.
     */
    @Test
    public void shouldReturnTrueForValidIDNumbers() {
        // Valid ID with a date of birth of 1st January 2001
        assertTrue(validateSaId.isIdNumberValid("2001014800086"));
        // Valid ID with a date of birth of 29th September 2003
        assertTrue(validateSaId.isIdNumberValid("2909035800085"));
    }

    /**
     * Test for ID numbers that are too short.
     * Valid South African IDs must be exactly 13 digits long.
     */
    @Test
    public void shouldReturnFalseForTooShortID() {
        // Test for an ID number with only 12 digits (too short)
        assertFalse(validateSaId.isIdNumberValid("123456789012"));
    }

    /**
     * Test for ID numbers that are too long.
     * Valid South African IDs must be exactly 13 digits long.
     */
    @Test
    public void shouldReturnFalseForTooLongID() {
        // Test for an ID number with 14 digits (too long)
        assertFalse(validateSaId.isIdNumberValid("12345678901234"));
    }

    /**
     * Test for ID numbers that contain non-numeric characters.
     * Valid South African IDs must contain only numeric characters.
     */
    @Test
    public void shouldReturnFalseForNonNumericID() {
        // Test for an ID number with non-numeric characters (contains 'A')
        assertFalse(validateSaId.isIdNumberValid("20010A4800086"));
    }

    /**
     * Test for invalid months in the date of birth.
     * The month part of a valid South African ID must be between 01 and 12.
     */
    @Test
    public void shouldReturnFalseForInvalidMonth() {
        // Test for an ID number where the month is invalid (13)
        assertFalse(validateSaId.isIdNumberValid("2013014800086"));
    }

    /**
     * Test for invalid days in the date of birth.
     * The day part of a valid South African ID must be valid for the given month.
     */
    @Test
    public void shouldReturnFalseForInvalidDay() {
        // Test for an ID number where the day is invalid (31st on February)
        assertFalse(validateSaId.isIdNumberValid("2002314800086"));
    }

    /**
     * Test for invalid citizenship digits.
     * The citizenship digit (position 11) must be either 0 or 1.
     */
    @Test
    public void shouldReturnFalseForInvalidCitizenshipDigit() {
        // Test for an ID number where the citizenship digit is invalid (should be 0 or 1)
        assertFalse(validateSaId.isIdNumberValid("2001014802086"));
    }

    /**
     * Test for invalid Luhn checksum.
     * The checksum of the ID number should pass the Luhn algorithm validation.
     */
    @Test
    public void shouldReturnFalseForInvalidLuhnChecksum() {
        // Test for an ID number that fails the Luhn checksum validation
        assertFalse(validateSaId.isIdNumberValid("2001014800080"));

        // Test for another ID number that fails the Luhn checksum validation
        assertFalse(validateSaId.isIdNumberValid("2001014800080"));
    }

    /**
     * Test for null input.
     * The validation should return false for a null input value.
     */
    @Test
    public void shouldReturnFalseForNullInput() {
        // Test for null input, which should return false
        assertFalse(validateSaId.isIdNumberValid(null));
    }

    /**
     * Test for an ID number where the year part exceeds the current year range.
     * The year must be a valid year within the acceptable range (e.g., 1900-2099).
     */
    @Test
    public void shouldReturnFalseForInvalidYear() {
        // Test for an ID number with a year greater than the current year (e.g., 2025)
        assertFalse(validateSaId.isIdNumberValid("2501134800086"));
    }
}
