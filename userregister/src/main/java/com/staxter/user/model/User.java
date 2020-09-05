package com.staxter.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
     @Id
     @GeneratedValue
     private Long id;
     private String firstName;
     private String lastName;
     private String userName;
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private String plainTextPassword;
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private String hashedPassword;
}
