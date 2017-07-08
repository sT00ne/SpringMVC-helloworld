package com.st.domain;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * Created by stone on 2017/7/4.
 */
public class UserValidator implements Validator {
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    public void validate(Object obj, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "name", null, "姓名为空");
        User user = (User) obj;
        if (null == user.getAge() || "".equals(user.getAge())) {
            errors.rejectValue("age", null, "年龄为空");
        }
    }
}
