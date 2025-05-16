package validateSaId;

import java.time.LocalDate;

public class validateSaId {

    /**
     * Main validation function to check the validity of a South African ID number.
     * The ID number is validated based on various criteria such as length,
     * date of birth, gender code, citizenship digit, and the Luhn checksum.
     *
     * @param idNumber The South African ID number to validate
     * @return boolean Returns true if the ID number is valid, false otherwise
     */
    public static boolean isIdNumberValid(String idNumber) {
        // Check length and digits
        if (!isValidLengthAndDigits(idNumber)) return false;
        // Validate Date of Birth
        if (!isValidDateOfBirth(idNumber.substring(0, 6))) return false;
        // Validate Gender Code
        if (!isValidGenderCode(idNumber.substring(6, 10))) return false;
        // Validate Citizenship Digit
        if (!isValidCitizenshipDigit(idNumber.charAt(10))) return false;
        // Validate Luhn Checksum
        return isValidLuhnChecksum(idNumber);
    }

    /**
     * Validates the length of the ID number and ensures that it contains only digits.
     * A valid South African ID number must be exactly 13 digits long.
     *
     * @param id The ID number to validate
     * @return boolean Returns true if the ID is valid in length and contains only digits
     */
    private static boolean isValidLengthAndDigits(String id) {
        return id != null && id.length() == 13 && id.matches("\\d{13}");
    }

    /**
     * Validates the Date of Birth part of the ID (first 6 digits: YYMMDD).
     * The function checks that the date is valid (month and day are correct, and the year is in range).
     *
     * @param dob The first 6 digits of the ID representing the Date of Birth (YYMMDD)
     * @return boolean Returns true if the date of birth is valid, false otherwise
     */
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

    /**
     * Validates the gender code (digits 7 to 10 of the ID number).
     * The gender code should be a 4-digit number between 0000 and 9999.
     *
     * @param genderCode The 4 digits representing the gender code in the ID
     * @return boolean Returns true if the gender code is valid, false otherwise
     */
    private static boolean isValidGenderCode(String genderCode) {
        try {
            int code = Integer.parseInt(genderCode);
            return code >= 0 && code <= 9999;
            // Valid gender codes are 0000 to 9999
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Validates the citizenship digit (digit at position 11 of the ID).
     * The citizenship digit should be either '0' (South African citizen) or '1' (permanent resident).
     *
     * @param digit The citizenship digit in the ID number (character at position 11)
     * @return boolean Returns true if the citizenship digit is valid, false otherwise
     */
    private static boolean isValidCitizenshipDigit(char digit) {
        return digit == '0' || digit == '1';
    }

    /**
     * Validates the Luhn checksum of the ID number.
     * The Luhn algorithm is used to validate the integrity of the ID number.
     *
     * @param id The 13-digit ID number to validate
     * @return boolean Returns true if the ID number passes the Luhn checksum, false otherwise
     */
    private static boolean isValidLuhnChecksum(String id) {
        int checksum = 0;  // Renamed from sum to checksum
        boolean alternate = false;

        // Loop through the digits in reverse order
        for (int i = id.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(id.charAt(i));
            // If the alternate flag is true, double the digit
            if (alternate) {
                digit *= 2;
                if (digit > 9) digit -= 9;  // Subtract 9 if the doubled digit is greater than 9
            }
            checksum += digit;  // Use checksum instead of sum
            alternate = !alternate; // Flip the alternate flag for the next digit
        }

        // The checksum must be divisible by 10 for the ID to be valid
        return checksum % 10 == 0;
    }
}
