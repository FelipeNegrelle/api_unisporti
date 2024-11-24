package com.unisporti.api_unisporti.model;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Integer idUser;

    @Column(name = "role", nullable = false, length = 1)
    private Character role = 'U';

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    private String cpf;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "password", length = 72)
    private String password;

    @Column(name = "active", nullable = false)
    private Boolean active = true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "training_user",
            joinColumns = @JoinColumn(name = "id_user", referencedColumnName = "id_user"),
            inverseJoinColumns = @JoinColumn(name = "id_training", referencedColumnName = "id_training")
    )
    private List<Training> trainings;

    public static String getUserFullName(User user) {
        return user.getFirstName() + " " + user.getLastName();
    }
}
