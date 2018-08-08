package language;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@ManagedBean(name="language", eager = true)
@ApplicationScoped
public class LanguageBean implements Serializable{

    private static final long serialVersionUID = 1L;

    private String localeCode;

    private static Map<String,Object> countries;

    static{
        countries = new LinkedHashMap<>();
        countries.put("English_US", Locale.ENGLISH); //label, value
        countries.put("Spanish_ES", new Locale.Builder().setLanguage("es").setRegion("ES").build()); //label, value
    }

    public Map<String, Object> getCountriesInMap() {
        return countries;
    }

    public String getLocaleCode() {
        return localeCode;
    }

    public void setLocaleCode(String localeCode) {
        this.localeCode = localeCode;
    }

    public void countryLocaleCodeChanged(ValueChangeEvent e){

        String newLocaleValue = e.getNewValue().toString();

        //loop country map to compare the locale code
        for (Map.Entry<String, Object> entry : countries.entrySet()) {

            if(entry.getValue().toString().equals(newLocaleValue)) {

                FacesContext.getCurrentInstance().getViewRoot().setLocale((Locale) entry.getValue());
                addMessage();
            }
        }
    }

    private void addMessage() {
        LanguageResource lrs = new LanguageResource();
        FacesContext.getCurrentInstance().addMessage
                (null, new FacesMessage(FacesMessage.SEVERITY_INFO, lrs.getLanguageResource("successfull"),  lrs.getLanguageResource("languageReverse")));
    }
}