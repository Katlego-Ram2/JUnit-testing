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
    }


