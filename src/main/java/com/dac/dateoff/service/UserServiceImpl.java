package com.dac.dateoff.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dac.dateoff.model.Role;
import com.dac.dateoff.model.User;
import com.dac.dateoff.repository.RoleRepository;
import com.dac.dateoff.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void save(User user) {
		userRepository.save(user);
		
	}

	@Override
	public void regisUser(User user) {
		String passEncode = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(passEncode);
		Set<Role> roles = roleRepository.findByName("USER");
		user.setRoles(roles);
		userRepository.save(user);
	}

}
