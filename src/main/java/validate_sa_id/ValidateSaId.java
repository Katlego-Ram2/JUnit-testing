package validate_sa_id;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

public class ValidateSaId {

    // Main ID validation function
    public static boolean isIdNumberValid(String idNumber) {
        // Check length and digits
        if (!isValidLengthAndDigits(idNumber)) return false;
        // Validate Date of Birth
        if (!isValidDateOfBirth(idNumber.substring(0, 6))) return false;
        if (!isValidGenderCode(idNumber.substring(6, 10))) return false;
        // Validate Gender Code
        if (!isValidCitizenshipDigit(idNumber.charAt(10))) return false;
        // Validate Citizenship Digit
        return isValidLuhnChecksum(idNumber);
        // Validate Luhn Checksum
    }

    // Validate length and that the ID number only contains digits
    private static boolean isValidLengthAndDigits(String id) {
        return id != null && id.length() == 13 && id.matches("\\d{13}");
    }

    // Validate the Date of Birth part of the ID (first 6 digits)
    private static boolean isValidDateOfBirth(String dob) {
        try {
            int year = Integer.parseInt(dob.substring(0, 2));
            // Extract year
            int month = Integer.parseInt(dob.substring(2, 4));
            // Extract month
            int day = Integer.parseInt(dob.substring(4, 6));
            // Extract day

            // Determine full year (20XX or 19XX)
            int currentYear = LocalDate.now().getYear() % 100;
            // Get last two digits of the current year
            int fullYear = (year <= currentYear) ? 2000 + year : 1900 + year;

            // Ensure that month is valid (1 to 12) and day is valid for that month/year
            LocalDate date = LocalDate.of(fullYear, month, day);
            // Parse date to check validity

            return date.getMonthValue() == month && date.getDayOfMonth() == day;
            // Check if month/day are correct
        } catch (Exception e) {
            return false;

        }
    }

    // Validate gender code (should be a 4-digit number)
    private static boolean isValidGenderCode(String genderCode) {

        try {
            int code = Integer.parseInt(genderCode);
            return code >= 0 && code <= 9999;
            // Valid gender codes are 0000 to 9999
        } catch (NumberFormatException e) {
            return false;
        }
    }

    // Validate citizenship digit (should be either '0' or '1')
    private static boolean isValidCitizenshipDigit(char digit) {
        return digit == '0' || digit == '1';
    }

    // Validate the Luhn checksum (used to validate the ID number)
    private static boolean isValidLuhnChecksum(String id) {
        int sum = 0;
        boolean alternate = false;

        for (int i = id.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(id.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;
            }
            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
