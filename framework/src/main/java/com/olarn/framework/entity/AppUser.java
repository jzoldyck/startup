package com.olarn.framework.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "app_user", persistenceUnit = "olarn")
@RooSerializable
public class AppUser extends OlarnObject {

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
    private Boolean enabled;

    /**
     */
    private Boolean tokenExpired;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppRole> appRoles = new HashSet<AppRole>();
}
