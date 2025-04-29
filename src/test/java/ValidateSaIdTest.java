import org.junit.jupiter.api.Test;
import validate_sa_id.ValidateSaId;

import static org.junit.jupiter.api.Assertions.*;

public class ValidateSaIdTest {

    @Test
    public void shouldReturnTrueForValidIDNumbers() {
        // Valid ID
        assertTrue(ValidateSaId.isIdNumberValid("2001014800086"));
        // Valid ID
        assertTrue(ValidateSaId.isIdNumberValid("2909035800085"));
    }

    // Test for ID numbers that are too short
    @Test
    public void shouldReturnFalseForTooShortID() {
        // 12 digits (too short)
        assertFalse(ValidateSaId.isIdNumberValid("123456789012"));
    }

    // Test for ID numbers that are too long
    @Test
    public void shouldReturnFalseForTooLongID() {
        // 14 digits (too long)
        assertFalse(ValidateSaId.isIdNumberValid("12345678901234"));
    }

    // Test for non-numeric ID numbers
    @Test
    public void shouldReturnFalseForNonNumericID() {
        // Contains non-numeric characters
        assertFalse(ValidateSaId.isIdNumberValid("20010A4800086"));
    }

    // Test for invalid month in the date of birth
    @Test
    public void shouldReturnFalseForInvalidMonth() {
        // Month 13 is invalid
        assertFalse(ValidateSaId.isIdNumberValid("2013014800086"));
    }

    // Test for invalid day in the date of birth
    @Test
    public void shouldReturnFalseForInvalidDay() {
        assertFalse(ValidateSaId.isIdNumberValid("2002314800086"));
    }

    // Test for invalid citizenship digit
    @Test
    public void shouldReturnFalseForInvalidCitizenshipDigit() {
        // Citizenship digit = 2 is invalid
        assertFalse(ValidateSaId.isIdNumberValid("2001014802086"));
    }

    // Test for invalid Luhn checksum
    @Test
    public void shouldReturnFalseForInvalidLuhnChecksum() {
        assertFalse(ValidateSaId.isIdNumberValid("2001014800080"));

        assertFalse(ValidateSaId.isIdNumberValid("2001014800080"));
    }

    // Test for null input (should return false)
    @Test
    public void shouldReturnFalseForNullInput() {
        // Null input
        assertFalse(ValidateSaId.isIdNumberValid(null));
    }

    // Test for an ID number where the year exceeds the current year range
    @Test
    public void shouldReturnFalseForInvalidYear() {
        // Invalid year (greater than current)
        assertFalse(ValidateSaId.isIdNumberValid("2501134800086"));
    }
}
