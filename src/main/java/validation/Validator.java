package validation;

import model.Customer;

import java.io.Serializable;
import java.util.regex.Pattern;

public class Validator implements Serializable {

    private static final long serialVersionUID = 3581515804518705261L;

    private static final String NAME_PATTERN = "^[a-zA-Z]{2,40}$";
    private static final String USERNAME_PATTERN = "^[a-z0-9-A-z]{2,40}$";
    private static final String NUMBER_PATTERN = "^[0-9]{2,30}$";
    //TODO:make email
    private static final String EMAIL_PATTERN = "^[a-zA-Z]{2,40}$";
    private Pattern pattern;

    /**
     * Validate customer with regular expression in methods below
     *
     * @param customer for validation
     * @return true valid input, false invalid input
     */
    public boolean validate(final Customer customer) throws Exception {
        try {
            if (!validateFirstName(customer.getFirstName()) ||
                    !validateLastName(customer.getLastName()) ||
                    !validateUserName(customer.getUserName()) ||
                    !validateCountry(customer.getCity()) ||
                    !validateCity(customer.getCity()) ||
                    !validateStreet(customer.getStreet()) ||
                    !validateZipCode(customer.getZipCode()) ||
                    !validatePhone(customer.getPhoneNumber()) ||
                    !validateEmail(customer.getEmail())) {

                return false;
            }
        } catch (Exception e) {
            throw new Exception ("Your input is wrong, try again!");
        }
        return true;
    }

    private boolean validateFirstName(String input) {
        pattern = Pattern.compile(NAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validateLastName(String input) {
        pattern = Pattern.compile(NAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validateUserName(String input) {
        pattern = Pattern.compile(USERNAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validateCountry(String input) {
        pattern = Pattern.compile(NAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
//        return result;
    return true;
    }

    private boolean validateCity(String input) {
        pattern = Pattern.compile(NAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
//        return result;
    return true;
    }

    private boolean validateStreet(String input) {
        pattern = Pattern.compile(NAME_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validateZipCode(String input) {
        pattern = Pattern.compile(NUMBER_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validatePhone(String input) {
        pattern = Pattern.compile(NUMBER_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }

    private boolean validateEmail(String input) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        boolean result = pattern.matcher(input).matches();
        return result;
    }
}
