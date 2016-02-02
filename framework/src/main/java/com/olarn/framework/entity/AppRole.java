package com.olarn.framework.entity;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.roo.addon.tostring.RooToString;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "app_role", persistenceUnit = "olarn")
@RooSerializable
public class AppRole extends OlarnObject {

    /**
     */
    @NotNull
    @Column(unique = true)
    private String name;

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<AppPrivilege> appPrivileges = new HashSet<AppPrivilege>();

    /**
     */
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "appRoles")
    private Set<AppUser> appUsers = new HashSet<AppUser>();
}
