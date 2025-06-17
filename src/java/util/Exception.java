package util;

import dal.PatientDAO;
import dal.ReceptionistDAO;
import java.util.List;
import model.Patient;
import model.Receptionist;

public class Exception {

    private static ReceptionistDAO rdao = new ReceptionistDAO();
    private static PatientDAO pdao = new PatientDAO();

    public static String getPasswordFormatForPatient(Patient p, String oldPass, String password, String cfPass) {
        String msg = "";

        if (!p.getPassword().equals(oldPass)) {
            msg += "Old password incorrect!!!";
            return msg;
        } else {
            if (password.isEmpty()) {
                msg += "Error: Password cannot be empty.";
                return msg;
            }

            if (password.contains(" ")) {
                msg += "Error: Password cannot contain spaces.";
                return msg;
            }

            if (password.length() < 8) {
                msg += "Error: Password must be at least 8 characters long.";
                return msg;
            }

            if (password.length() > 16) {
                msg += "Error: Password cannot be longer than 16 characters.";
                return msg;
            }

            boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
            for (char c : password.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    hasLower = true;
                }
                if (Character.isUpperCase(c)) {
                    hasUpper = true;
                }
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (!Character.isLetterOrDigit(c)) {
                    hasSpecial = true;
                }
            }

            if (!hasLower) {
                msg += "Error: Password must contain at least one lowercase letter.";
                return msg;
            }

            if (!hasUpper) {
                msg += "Error: Password must contain at least one uppercase letter.";
                return msg;
            }

            if (!hasDigit) {
                msg += "Error: Password must contain at least one digit.";
                return msg;
            }

            if (!hasSpecial) {
                msg += "Error: Password must contain at least one special character.";
                return msg;
            }

            if (!password.equals(cfPass)) {
                msg += "Error: New password does not match confirm password.";
                return msg;
            }
        }
        return null; // Empty string if all checks pass
    }

    public static String getPasswordFormatForReceptionist(Receptionist r, String oldPass, String password, String cfPass) {
        String msg = "";

        if (!r.getPassword().equals(oldPass)) {
            msg += "Old password incorrect!!!";
            return msg;
        } else {
            if (password.isEmpty()) {
                msg += "Error: Password cannot be empty.";
                return msg;
            }

            if (password.contains(" ")) {
                msg += "Error: Password cannot contain spaces.";
                return msg;
            }

            if (password.length() < 8) {
                msg += "Error: Password must be at least 8 characters long.";
                return msg;
            }

            if (password.length() > 16) {
                msg += "Error: Password cannot be longer than 16 characters.";
                return msg;
            }

            boolean hasLower = false, hasUpper = false, hasDigit = false, hasSpecial = false;
            for (char c : password.toCharArray()) {
                if (Character.isLowerCase(c)) {
                    hasLower = true;
                }
                if (Character.isUpperCase(c)) {
                    hasUpper = true;
                }
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (!Character.isLetterOrDigit(c)) {
                    hasSpecial = true;
                }
            }

            if (!hasLower) {
                msg += "Error: Password must contain at least one lowercase letter.";
                return msg;
            }

            if (!hasUpper) {
                msg += "Error: Password must contain at least one uppercase letter.";
                return msg;
            }

            if (!hasDigit) {
                msg += "Error: Password must contain at least one digit.";
                return msg;
            }

            if (!hasSpecial) {
                msg += "Error: Password must contain at least one special character.";
                return msg;
            }

            if (!password.equals(cfPass)) {
                msg += "Error: New password does not match confirm password.";
                return msg;
            }
        }
        return null; // Empty string if all checks pass
    }

    public static String duplicateCodeError(String identity, String insurance, String phone) {
        String error = "";

        List<Patient> list = rdao.getListPatient();

        for (Patient patient : list) {
            if (patient.getIdentity().equals(identity)
                    || patient.getInsurance().equals(insurance)
                    || patient.getPhone().equals(phone)) {
                error += "identity or insurance or phone is existed!!!";
                return error;
            }
        }
        if (!phone.matches("^0\\d{9}$")) {
            error = "phone number not exceed 10 number";
            return error;
        }
        if (!identity.matches("^\\d{12}$")) {
            error = "identity not exceed 12 number";
            return error;
        }
        if (!insurance.matches("^\\d{10}$")) {
            error = "insurance not exceed 10 number";
            return error;
        }
        return null;
    }

}
