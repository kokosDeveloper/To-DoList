package validators;

import java.util.ArrayList;
import java.util.List;

public class PasswordValidator {
    public List<String> validate(String value){
        List<String> passwordErrorMessages = new ArrayList<>();
        if(value.length() <= 4)
            passwordErrorMessages.add("Пароль должен содержать больше 4 символов");
        if(!value.contains("@"))
            passwordErrorMessages.add("Пароль должен содержать @");
        return passwordErrorMessages;
    }
}
