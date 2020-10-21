package com.example.onlinedonationproject;

public class User {

    public String  organztn,emaill,bkashh,type;

    public User()
    {

    }

    public User(String organztn, String emaill, String bkashh,String type) {
        this.organztn = organztn;
        this.emaill = emaill;
        this.bkashh = bkashh;
        this.type=type;
    }

    public  User(String emaill, String bkashh,String type)
    {
        this.emaill=emaill;
        this.bkashh=bkashh;
        this.type=type;
    }

    public String getOrganztn()
    {
        return organztn;
    }
    public String getEmaill()
    {
        return emaill;

    }

    public String getBkashh()
    {
        return bkashh;
    }

    public void setOrganztn(String organztn) {
        this.organztn = organztn;
    }

    public void setEmaill(String emaill) {
        this.emaill = emaill;
    }

    public void setBkashh(String bkashh) {
        this.bkashh = bkashh;
    }

    public String gettype(){
        return type;
    }
    public void settype()
    {
        this.type=type;
    }
}
