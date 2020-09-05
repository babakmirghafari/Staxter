package com.staxter.user.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * User Model base on task document
 */
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
     /**
      * This two field should not present in return JSONObject. for cover this requirement
      * change fields JSONProperty to write only. With this annotation you can write to fields
      * but this fields absent in return JSONObject.
      */
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private String plainTextPassword;
     @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
     private String hashedPassword;
}
