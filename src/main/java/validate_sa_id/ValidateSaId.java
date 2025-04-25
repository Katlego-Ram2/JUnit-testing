package validate_sa_id;

public class ValidateSaId {

    public static boolean isIdNumberValid(String idNumber) {

        //Validation
        if (idNumber == null || idNumber.length() != 13 || !idNumber.matches("\\d+")) {
            return false;
        }
        return false;
    }

}

