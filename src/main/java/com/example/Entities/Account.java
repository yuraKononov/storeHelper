package com.example.Entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account")
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    public Account(boolean active) {
        this.active = active;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", length = 11, nullable = false)
    private int user_id;

    @Column(name = "enabled", length = 1, nullable = true)
    private boolean active;

    public int getUser_id() {
        return user_id;
    }

    public boolean isActive() {
        return active;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return String.format(
                "Account[id=%d]",
                user_id);
    }
}
