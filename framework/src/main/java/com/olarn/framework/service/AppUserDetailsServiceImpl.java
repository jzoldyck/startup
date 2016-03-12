package com.olarn.framework.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.olarn.framework.entity.AppPrivilege;
import com.olarn.framework.entity.AppRole;
import com.olarn.framework.entity.AppUser;
import com.olarn.framework.repository.AppUserRepository;

@Service("appUserDetailsService")
@Transactional
public class AppUserDetailsServiceImpl implements UserDetailsService {
	
	private static final Logger logger = Logger.getLogger(AppUserDetailsServiceImpl.class);
	
	@Autowired
	private AppUserRepository repoAppUser;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		logger.debug(username);
		AppUser appUser = repoAppUser.findByUsername(username);
		logger.debug(appUser);
		if (null != appUser) {
//			return new org.springframework.security.core.userdetails.User(
//					appUser.getUsername(), appUser.getPassword(), appUser.getEnabled(), true,
//					appUser.getTokenExpired(), true, getAuthorities(appUser.getAppRoles()));
			return new org.springframework.security.core.userdetails.User(
					appUser.getUsername(), appUser.getPassword(), true, true,
					true, true, getAuthorities(appUser.getAppRoles()));
		}
		return null;
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(Collection<AppRole> appRoles) {
		return getGrantedAuthorities(getAppPrivileges(appRoles));
	}
	
	private Set<String> getAppPrivileges(Collection<AppRole> appRoles) {
		Set<String> appPrivileges = new HashSet<String>();
		List<AppPrivilege> tmpPrivileges = new ArrayList<AppPrivilege>();
		for (AppRole appRole : appRoles) {
			tmpPrivileges.addAll(appRole.getAppPrivileges());
		}
		for (AppPrivilege privilege : tmpPrivileges) {
			appPrivileges.add(privilege.getName());
		}
		return appPrivileges;
	}
	
	private List<GrantedAuthority> getGrantedAuthorities(Set<String> appPrivileges) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (String privilegeName : appPrivileges) {
			authorities.add(new SimpleGrantedAuthority(privilegeName));
		}
		return authorities;
	}

}
