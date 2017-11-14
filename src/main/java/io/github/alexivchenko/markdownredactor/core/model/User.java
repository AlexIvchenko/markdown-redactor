package io.github.alexivchenko.markdownredactor.core.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author Alex Ivchenko
 */
@Entity
@Table(name = "users")
@EqualsAndHashCode(of = "username")
@Getter
@Setter
@ToString(of = "username")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
