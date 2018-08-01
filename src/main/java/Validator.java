import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator implements Serializable {
    private static final long serialVersionUID = 3581515804518705261L;
    private Pattern pattern;

    public Validator(String REGEX){
        pattern = Pattern.compile(REGEX);
    }

    /**
     * Validate username with regular expression
     * @param input input for validation
     * @return true valid input, false invalid input
     */
    public boolean validate(final String input) {
        Matcher matcher = pattern.matcher(input);
        return matcher.matches();
    }

    public boolean validateDate(final Date input) {
        Matcher matcher = pattern.matcher(input.toString());

        if(matcher.matches()){

            matcher.reset();

            if(matcher.find()){

                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));

                if (day.equals("31") &&
                        (month.equals("4") || month .equals("6") || month.equals("9") ||
                                month.equals("11") || month.equals("04") || month .equals("06") ||
                                month.equals("09"))) {
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                } else if (month.equals("2") || month.equals("02")) {
                    //leap year
                    if(year % 4==0){
                        if(day.equals("30") || day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }else{
                        if(day.equals("29")||day.equals("30")||day.equals("31")){
                            return false;
                        }else{
                            return true;
                        }
                    }
                }else{
                    return true;
                }
            }else{
                return false;
            }
        }else{
            return false;
        }
    }

}
