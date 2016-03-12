package com.olarn.framework.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.entity.RooJpaEntity;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaEntity(table = "app_user")
@RooSerializable
public class AppUser extends OlarnObject {

    @NotNull
    @Column(unique = true)
    private String username;

    /**
     */
    private String firstName;

    /**
     */
    private String lastName;

    /**
     */
    @NotNull
    @Column(unique = true)
    @Pattern(regexp = "/^\\S+@\\S+\\.\\S+$/")
    private String email;

    /**
     */
    @NotNull
    private String password;

    /**
     */
    private boolean enabled;

    /**
     */
    private boolean tokenExpired;

    /**
     */
    @ManyToMany
    @JoinTable(
    		name = "app_users_app_roles",
    		joinColumns = @JoinColumn(name = "app_user_id", referencedColumnName = "id"),
    		inverseJoinColumns = @JoinColumn(name = "app_role_id", referencedColumnName = "id"))
    private Set<AppRole> appRoles = new HashSet<AppRole>();
}
