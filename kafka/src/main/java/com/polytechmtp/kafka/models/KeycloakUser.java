package com.polytechmtp.kafka.models;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="user_entity")
@Access(AccessType.FIELD)
public class KeycloakUser {

    @Id
    private String id;
    private String email;
    private String email_constraint;
    private boolean email_verified;
    private boolean enabled;
    private String first_name;
    private String last_name;
    private String realm_id;
    private String username;
    private String state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail_constraint() {
        return email_constraint;
    }

    public void setEmail_constraint(String email_constraint) {
        this.email_constraint = email_constraint;
    }

    public boolean isEmail_verified() {
        return email_verified;
    }

    public void setEmail_verified(boolean email_verified) {
        this.email_verified = email_verified;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getRealm_id() {
        return realm_id;
    }

    public void setRealm_id(String realm_id) {
        this.realm_id = realm_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
