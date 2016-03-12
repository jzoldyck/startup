package com.olarn.framework.repository;
import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.roo.addon.layers.repository.jpa.RooJpaRepository;

import com.olarn.framework.entity.AppRole;

@RooJpaRepository(domainType = AppRole.class)
public interface AppRoleRepository {

//	@Query("SELECT ar FROM olarn.app_role ar WHERE name in :names")
//	public List<AppRole> findAllByNameIn(@Param("names") List<String> names);
	public List<AppRole> findAllByNameIn(Collection<String> names);
	@Query( "select o from AppRole o where o.name in :names" )
	public List<AppRole> findByName(@Param("names") List<String> names);
	public AppRole findByName(String name);
}
