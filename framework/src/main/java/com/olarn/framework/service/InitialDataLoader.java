package com.olarn.framework.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.olarn.framework.entity.AppRole;
import com.olarn.framework.entity.AppUser;
import com.olarn.framework.repository.AppRoleRepository;
import com.olarn.framework.repository.AppUserRepository;

@Component
public class InitialDataLoader {
	
	private static final Logger logger = Logger.getLogger(InitialDataLoader.class);
	
	private static final String[] STD_ROLE = {
		"ROLE_ADMIN", "ROLE_USER",
	};
	private static final String[] STD_USERNAME = {
		"admin",
	};

	@Autowired
	private AppRoleRepository repoAppRole;
	@Autowired
	private AppUserRepository repoAppUser;

	@EventListener
	public void handleContextRefresh(ContextRefreshedEvent event) {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>> handleContextRefresh <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		configRole();
		configUser();

	}
	
	@Transactional
	private void configRole() {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>> config role <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		for (int i=0; i<STD_ROLE.length; i ++) {
			logger.debug("Find " + STD_ROLE[i]);
			AppRole role = repoAppRole.findByName(STD_ROLE[i]);
			if (null == role) {
				logger.debug("not found. Create " + STD_ROLE[i]);
				AppRole appRole = new AppRole();
				appRole.setName(STD_ROLE[i]);
				repoAppRole.save(appRole);
			}
		}
	}
	
	@Transactional
	private void configUser() {
		logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>> config user <<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
		List<AppRole> setRole = null;
		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		for (int i=0; i<STD_USERNAME.length; i ++) {
			logger.debug("Find " + STD_USERNAME[i]);
			AppUser username = repoAppUser.findByUsername(STD_USERNAME[i]);
			if (null == username) {
				if (null == setRole) {
					setRole = repoAppRole.findByName(Arrays.asList(STD_ROLE));
				}
				logger.debug("not found. Create " + STD_USERNAME[i]);
				AppUser appUser = new AppUser();
				appUser.setUsername(STD_USERNAME[i]);
				appUser.setAppRoles(new HashSet<AppRole>(setRole));
				appUser.setEnabled(true);
				appUser.setPassword(passwordEncoder.encode("password"));
				appUser.setEmail("blabla@blabla.com");
				repoAppUser.save(appUser);
			}
		}
	}

}
