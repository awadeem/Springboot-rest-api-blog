package com.springboot.blog.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
// username and email is unique key in our table
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"email"})})

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String username;
    private String email;
    private String password;

    //EAGER is used for retrieving not ON Demand
    //Casecade whenever parents perform some function child automatically do that
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //create third table
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;
}
