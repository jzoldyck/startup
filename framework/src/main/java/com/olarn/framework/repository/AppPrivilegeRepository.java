package com.olarn.framework.repository;
import com.olarn.framework.entity.AppPrivilege;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AppPrivilege.class)
public interface AppPrivilegeRepository {
}
