package validate_sa_id;

public class ValidateSaId {



        public static boolean isIdNumberValid (String idNumber){
            if (!isValidLengthAndDigits(idNumber)) return false;
            if (!isValidDateOfBirth(idNumber.substring(0, 6))) return false;
            if (!isValidGenderCode(idNumber.substring(6, 10))) return false;
            if (!isValidCitizenshipDigit(idNumber.charAt(10))) return false;
            return isValidLuhnChecksum(idNumber);
        }

        private static boolean isValidLengthAndDigits(String id) {
            return id != null && id.length() == 13 && id.matches("\\d{13}");
        }

    private static boolean isValidDateOfBirth(String dob) {
        int year = Integer.parseInt(dob.substring(0, 2));
        int month = Integer.parseInt(dob.substring(2, 4));
        int day = Integer.parseInt(dob.substring(4, 6));

        int currentYear = LocalDate.now().getYear() % 100;
        int fullYear = (year <= currentYear) ? 2000 + year : 1900 + year;

        String dateStr = String.format("%04d%02d%02d", fullYear, month, day);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd")
                .withResolverStyle(ResolverStyle.STRICT);

        try {
            LocalDate.parse(dateStr, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    }


