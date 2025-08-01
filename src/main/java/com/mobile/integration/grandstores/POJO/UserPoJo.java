package com.mobile.integration.grandstores.POJO;

public class UserPoJo {
    

    public String UserName;
    public String PersonId;

    public String getUserName() {
        return UserName;
    }
    public void setUserName(String userName) {
        UserName = userName;
    }
    public String getPersonId() {
        return PersonId;
    }
    public void setPersonId(String personId) {
        PersonId = personId;
    }
    public UserPoJo(String userName, String personId) {
        UserName = userName;
        PersonId = personId;
    }
    public UserPoJo() {
    }

    
    

}