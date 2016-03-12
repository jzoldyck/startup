package com.olarn.framework.repository;
import com.olarn.framework.entity.AppUser;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

@RooJpaRepository(domainType = AppUser.class)
public interface AppUserRepository {
	
	public AppUser findByUsername(String username);
}
