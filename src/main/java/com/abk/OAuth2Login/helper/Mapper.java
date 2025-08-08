package com.abk.OAuth2Login.helper;

import com.abk.OAuth2Login.forms.UserRegForm;
import com.abk.OAuth2Login.model.User;

public class Mapper {

    public static User mapToUser(UserRegForm userRegForm)
    {
        return new User(userRegForm.getRegPass(),userRegForm.getRegName(),userRegForm.getRegEmail(),userRegForm.getRegProfilePic());
    }
}
