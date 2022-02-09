package jp.co.axa.apidemo.services;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jp.co.axa.apidemo.constants.JWTConstant;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	private static final Logger LOG = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(LOG.isDebugEnabled()) {
			LOG.debug("Inside JwtUserDetailsService - loadUserByUsername -- username {}", username);
		}
		if (JWTConstant.USERNAME.equals(username)) {
			return new User(JWTConstant.USERNAME, JWTConstant.BCRYPT_PASSWORD,
					new ArrayList<>());
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}

}