package com.abk.OAuth2Login.forms;

public class UserRegForm {
    private String regName;
    private String regEmail;
    private String regPass;
    private String regProfilePic;

    public String getRegPass() {
        return regPass;
    }

    public UserRegForm() {
    }

    public void setRegPass(String regPass) {
        this.regPass = regPass;
    }

    public String getRegProfilePic() {
        return regProfilePic;
    }

    public void setRegProfilePic(String regProfilePic) {
        this.regProfilePic = regProfilePic;
    }

    public UserRegForm(String regName, String regEmail, String regPass, String regProfilePic) {
        this.regName = regName;
        this.regEmail = regEmail;
        this.regPass = regPass;
        this.regProfilePic = regProfilePic;
    }

    public UserRegForm(String regName, String regEmail) {
        this.regName = regName;
        this.regEmail = regEmail;
    }

    public String getRegName() {
        return regName;
    }

    public void setRegName(String regName) {
        this.regName = regName;
    }

    public String getRegEmail() {
        return regEmail;
    }

    public void setRegEmail(String regEmail) {
        this.regEmail = regEmail;
    }
}
