package com.polytechmtp.contacts.models;

import javax.persistence.*;
import java.util.Date;

@Entity(name="contacts")
@Access(AccessType.FIELD)
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long contact_id;
    private String user_id_contact_1;
    private String user_id_contact_2;
    private Date date_contact;

    public long getContact_id() {
        return contact_id;
    }

    public void setContact_id(long contact_id) {
        this.contact_id = contact_id;
    }

    public String getUser_id_contact_1() {
        return user_id_contact_1;
    }

    public void setUser_id_contact_1(String user_id_contact_1) {
        this.user_id_contact_1 = user_id_contact_1;
    }

    public String getUser_id_contact_2() {
        return user_id_contact_2;
    }

    public void setUser_id_contact_2(String user_id_contact_2) {
        this.user_id_contact_2 = user_id_contact_2;
    }

    public Date getDate_contact() {
        return date_contact;
    }

    public void setDate_contact(Date date_contact) {
        this.date_contact = date_contact;
    }
}
