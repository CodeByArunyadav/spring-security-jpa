package com.springsecurityjpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
//this class makes use of an implementation of UserRepository, which will be created and injected by Spring Data JPA. 
//And we override the loadUserByUsername() method to authentication the users.
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
	private UserRepository userrepository;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user=userrepository.getUserByUsername(username);
		if (user==null)
		{
			throw new UsernameNotFoundException("Could not found the user");
		}
			return new MyUserDetails(user);
	}

}
