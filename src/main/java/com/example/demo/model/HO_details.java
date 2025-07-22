
package com.example.demo.model;

public class HO_details
 {
    private String name;
    private String contactAddress ;
    private String doc ;
    private String email_phone ;
    private String latitude;
    private String longitude ;
    private String password ;
    private String ref_code ;
    private String reg_date ;
    private String rej_reason ;
    private String username ;
    private String verification ;

    public HO_details(String name ,String contactAddress ,String doc ,String email_phone ,String latitude ,
    String longitude ,String password ,String ref_code ,String reg_date ,String rej_reason ,String username ,
    String verification ) 
    {
        this.name = name;
        this.contactAddress = contactAddress;
        this.doc = doc;
        this.email_phone = email_phone;
        this.latitude = latitude;
        this.longitude = longitude;
        this.password = password;
        this.ref_code = ref_code;
        this.reg_date = reg_date;
        this.rej_reason = rej_reason;
        this.username = username;

    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getcontactAddress() { return contactAddress; }
    public void setcontactAddress(String contactAddress) { this.contactAddress = contactAddress; }

    public String getdoc() { return doc; }
    public void setdoc(String doc) { this.doc = doc; }

    public String getemail_phone() { return email_phone; }
    public void setemail_phone(String email_phone) { this.email_phone = email_phone; }

    public String getlatitude() { return latitude; }
    public void setlatitude(String latitude) { this.latitude = latitude; }

    public String getlongitude() { return longitude; }
    public void setlongitude(String longitude) { this.longitude = longitude; }

    public String getpassword() { return password; }
    public void setpassword(String password) { this.password = password; }

    public String getref_code() { return ref_code; }
    public void setref_code(String ref_code) { this.ref_code = ref_code; }

    public String getreg_date() { return reg_date; }
    public void setreg_date(String reg_date) { this.reg_date = reg_date; }

    public String getrej_reason() { return rej_reason; }
    public void setrej_reason(String rej_reason) { this.rej_reason = rej_reason; }

    public String getusername() { return username; }
    public void setusername(String username) { this.username = username; }

   
}
