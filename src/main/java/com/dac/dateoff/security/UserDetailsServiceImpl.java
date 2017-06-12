package com.dac.dateoff.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dac.dateoff.model.Role;
import com.dac.dateoff.model.User;
import com.dac.dateoff.repository.RoleRepository;
import com.dac.dateoff.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		System.out.println("---------------loadUserByUsername-----------------:" + email);
		try {
            User user = userRepository.findByEmail(email);
            
            if (user == null) {
                return null;
            }
            System.out.println("_________________________" + user.getUserId());
            List<Role> roles = roleRepository.findByUsers_UserId(user.getUserId());
            
            Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
	        for(Role role : roles) {
	            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
	            authorities.add(grantedAuthority);
	        }
	        
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
        }
        catch (Exception e){
        	e.printStackTrace();
            throw new UsernameNotFoundException("User not found");
        }
	}


}
