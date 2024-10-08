//package com.unisporti.api_unisporti.model;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.io.Serial;
//import java.io.Serializable;
//import java.util.Date;
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Setter
//@Getter
//@EqualsAndHashCode
//@ToString
//@Entity
//@Table(name = "users")
//public class User implements Serializable {
//    @Serial
//    private static final long serialVersionUID = 1L;
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_user")
//    private Long idUser;
//
//    @JoinColumn(name = "id_role")
//    @OneToOne(fetch = FetchType.LAZY)
//    private Role role;
//
//    @Column(name = "cpf", length = 11, nullable = false, unique = true)
//    private String cpf;
//
//    @Column(name = "first_name", nullable = false)
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    private String lastName;
//
//    @Column(name = "birth_date")
//    @Temporal(TemporalType.DATE)
//    private Date birthDate;
//
//    @Column(name = "email", nullable = false, unique = true)
//    private String email;
//
//    @Column(name = "phone", length = 13)
//    private String phone;
//
//    @Column(name = "password", length = 72)
//    private String password;
//
//    @Column(name = "active", nullable = false)
//    private Boolean active = true;
//}
