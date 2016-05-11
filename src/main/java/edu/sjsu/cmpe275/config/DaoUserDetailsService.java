package edu.sjsu.cmpe275.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import edu.sjsu.cmpe275.dao.UserDao;
import edu.sjsu.cmpe275.domain.User;

@Component
public class DaoUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao ;
	
	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		List<User> user = userDao.findByEmail(username);
		if (user.isEmpty()) {
			throw new UsernameNotFoundException(username + " not found.");
		} else if (user.size() > 1) {
			throw new UsernameNotFoundException(username + " not found.");
		}
		User u = user.get(0);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(u.getRole()));
		return new org.springframework.security.core.userdetails.User(
				   u.getEmail(),
				   u.getPassword(),
				   u.isActive(),
				   true,
				   true,
				   true,
				   authorities);
	}

}
