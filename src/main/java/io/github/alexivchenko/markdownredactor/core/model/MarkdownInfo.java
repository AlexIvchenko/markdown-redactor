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
@Table(name = "docs", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "owner_id"})
})
@EqualsAndHashCode(of = {"owner", "name"})
@ToString(of = {"owner", "name"})
@Setter
@Getter

public class MarkdownInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    public MarkdownInfo() {
    }

    public MarkdownInfo(User owner, String name) {
        this.owner = owner;
        this.name = name;
    }
}
