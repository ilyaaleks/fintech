package hes.fintech.domain;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Service
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return UserAccount.class.equals(aClass);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        UserAccount p = (UserAccount) obj;
        if (!p.getFirstname().matches("\\p{L}+")) {
            errors.rejectValue("firstname", "value.invalid");
        }
        else if(!p.getLastname().matches("\\p{L}+"))
        {
            errors.rejectValue("lastname", "value.invalid");
        }
        else if(!p.getUsername().matches("\\p{L}+"))
        {
            errors.rejectValue("username", "value.invalid");
        }
    }
}
