package com.flag.robot_dispatch.model;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "user")
@JsonDeserialize(builder = User.Builder.class)
public class User implements Serializable {
   private static final long serialVersionUID = 1L;
   @Id
   private String username;
   @JsonIgnore             //new added
   private String password;
   @JsonIgnore             //new added
   private boolean enabled;

   @OneToMany(mappedBy = "guest", fetch = FetchType.EAGER)     //new added
   private List<Order> orders;                                    //new added

   public User() {}

   private User(Builder builder) {
      this.username = builder.username;
      this.password = builder.password;
      this.enabled = builder.enabled;
   }

   public String getUsername() {
      return username;
   }

   public User setUsername(String username) {
      this.username = username;
      return this;
   }

   public String getPassword() {
      return password;
   }

   public User setPassword(String password) {
      this.password = password;
      return this;
   }

   public boolean isEnabled() {
      return enabled;
   }

   public User setEnabled(boolean enabled) {
      this.enabled = enabled;
      return this;
   }

   public static class Builder {
      @JsonProperty("username")
      private String username;

      @JsonProperty("password")
      private String password;

      @JsonProperty("enabled")
      private boolean enabled;

      public Builder setUsername(String username) {
         this.username = username;
         return this;
      }

      public Builder setPassword(String password) {
         this.password = password;
         return this;
      }

      public Builder setEnabled(boolean enabled) {
         this.enabled = enabled;
         return this;
      }

      public User build() {
         return new User(this);
      }
   }
}
