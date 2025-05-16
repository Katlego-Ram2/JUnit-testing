package validateSaId;

/**
 * Main class to demonstrate validating South African ID numbers.
 */
public class Main {
    public static void main(String[] args) {
        // Sample South African ID numbers for validation
        String[] idNumbers = {
                "2001014800086", // Valid ID
                "2909035800085", // Valid ID
                "123456789012",  // Too short
                "12345678901234", // Too long
                "20010A4800086", // Invalid (contains non-numeric character)
                "2013014800086", // Invalid month (13)
                "2002314800086", // Invalid day (31st on February)
                "2001014802086", // Invalid citizenship digit
                "2501134800086", // Invalid year (greater than current)
                "2001014800080"  // Invalid Luhn checksum
        };

        // Iterate over each ID number and validate it
        for (String idNumber : idNumbers) {
            // Print the result of the validation for each ID number
            boolean isValid = validateSaId.isIdNumberValid(idNumber);
            System.out.printf("ID Number: %s is %s%n", idNumber, isValid ? "Valid" : "Invalid");
        }
    }
}
