package pro.sky.library.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.library.exception.BadRequestName;
import pro.sky.library.exception.BadRequestSurname;

@Service
public class ValidatorService {
    public String validatorName(String name) {
        String[] names = name.split("-");
        for (int i = 0; i < names.length; i++) {
            if (!StringUtils.isAlpha(names[i])) {
                throw new BadRequestSurname();
            }
            names[i] = StringUtils.capitalize(name.toLowerCase());
        }
        return String.join("-", names);
    }

    public String validatorSurname(String surname) {
        String[] surnames = surname.split("-");
        for (int i = 0; i < surnames.length; i++) {
            if (!StringUtils.isAlpha(surnames[i])) {
                throw new BadRequestSurname();
            }
            surnames[i] = StringUtils.capitalize(surname.toLowerCase());
        }
        return String.join("-", surnames);

    }

}
