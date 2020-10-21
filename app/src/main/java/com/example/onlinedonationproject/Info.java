package com.example.onlinedonationproject;

public class Info {
    private String tt;
    private String dd;
    private String or;
    private String bk;
    private String em;

    public Info()
    {

    }

   public Info(String tt,String dd,String or,String bk,String em)
   {
       this.tt=tt;
       this.dd=dd;
       this.or=or;
       this.bk=bk;
       this.em=em;
   }

    public String getTitlee() {
        return tt;
    }

    public String getDidlee()
    {
        return dd;
    }

    public void setTitlee(String tt) {
        this.tt=tt;
    }

    public void setDidlee(String tt) {
        this.dd = dd;
    }

    public String getOr()
    {
        return or;
    }

    public void setOr(String or) {
        this.or=or;
    }

    public String getBk()
    {
        return bk;
    }

    public void setBk(String bk) {
        this.bk=bk;
    }
    public String getEm()
    {
        return em;
    }

    public void setEm()
    {
        this.em=em;
    }

}
