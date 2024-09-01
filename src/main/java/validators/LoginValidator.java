package validators;

import entity.User;
import lombok.AllArgsConstructor;
import services.UserService;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
public class LoginValidator {
    private UserService userService;
    public List<String> validate(String value){
        List<String> loginErrorMessages = new ArrayList<>();
        boolean userExist = userService.getAll().stream()
                .map(User::getLogin)
                .anyMatch(login -> login.equals(value));
        if(userExist)
            loginErrorMessages.add("Такой пользователь уже существует");
        if(value.length() <=4)
            loginErrorMessages.add("Логин должен содержать больше 4 символов");
        if(value.contains(" "))
            loginErrorMessages.add("Логин не должен содержать пробелов");
        return loginErrorMessages;
    }
}
