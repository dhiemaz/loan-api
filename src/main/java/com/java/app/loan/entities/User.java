package com.java.app.loan.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.java.app.loan.models.BaseRS;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Data
public class User extends BaseRS implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", length = 11)
    private int id;

    @JsonProperty("fullname")
    @NotNull(message = "fullname can't be null")
    @NotEmpty(message = "fullname can't be empty")
    @Column(name = "fullname", length = 150)
    private String fullname;

    @JsonProperty("username")
    @NotNull(message = "username can't be null")
    @NotEmpty(message = "username can't be empty")
    @Column(name = "username", length= 100)
    private String username;

    @JsonProperty("address")
    @NotNull(message = "address can't be null")
    @NotEmpty(message = "address can't be empty")
    @Column(name = "address", length = 200)
    private String address;

    @JsonProperty("phonePrefix")
    @NotNull(message = "phonePrefix can't be null")
    @NotEmpty(message = "phonePrefix can't be empty")
    @Column(name = "phone_prefix", length = 4)
    private String phonePrefix;

    @JsonProperty("phoneNumber")
    @NotNull(message = "phoneNumber can't be null")
    @NotEmpty(message = "phoneNumber can't be empty")
    @Column(name = "phone_number", length = 13)
    private String phoneNumber;
}
