package language;

import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageResource  implements Serializable{

    private static final long serialVersionUID = 5832557409506265601L;

    public String getLanguageResource(String input){
        Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
        ResourceBundle resourceBundle = ResourceBundle.getBundle("languageFiles.language",locale);
        String message = resourceBundle.getString(input);

        return message;
    }

}
