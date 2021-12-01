package com.swing.foodserving.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.dao.UserDao;
import com.swing.foodserving.entity.User;

@Component
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserDao userDao; 

	
	

	/*	public UserDao getUserdao() {
		return userdao;
	}

	public void setUserdao(UserDaoImpl userdao) {
		this.userdao = userdao;
	}*/


	@Override
	public UserDetails loadUserByUsername(String userName) {
		System.out.println("email"+userName);
		User user = userDao.findByUserName(userName);
		if (user == null) {
			throw new UsernameNotFoundException("This User not found" + userName);
		}
		else
		{
			List<GrantedAuthority> authorities = buildUserAuthority(user.getRole());	
			return (UserDetails) buildUserForAuthentication(user, authorities);
		}

	}
	
	private UserDetails buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
		// TODO Auto-generated method stu
		boolean active;
		if(user.getIsActive()==Constant.ACTIVE)
		{
			active=true;
		}else {
			active=false;
		}
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), user.getPassword(), true, true, true, active, authorities);
	}
	

	private List<GrantedAuthority> buildUserAuthority(int userRole) {
		List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>();
		if (userRole == Constant.ROLE_ADMIN) {
			Result.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if (userRole == Constant.ROLE_VENDOR) {
			Result.add(new SimpleGrantedAuthority("ROLE_VENDOR"));
		}else {
			Result.add(new SimpleGrantedAuthority("ROLE_PUBLIC"));
		}
		return Result;
	}

}
