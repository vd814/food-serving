package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.dao.UserDao;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.utility.PasswordEncoder;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	UserDao userDao;
	
	@Override
	public List getAllUserWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		
		return userDao.getAllUserWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
	}

	@Override
	public void registerUser(int role, String email, String userName, String password) {
		User user = new User(userName, PasswordEncoder.getEncodedPassword(password), email, role);
		userDao.saveOrUpdateUser(user);
	}

	@Override
	public void addUser(String name, String userName, String password, String mail, String mobile, int gender,
			int role, int mobileVerified,int emailVerified) {
		
		User user = new User(userName, PasswordEncoder.getEncodedPassword(password), mail, role);
		user.setName(name);
		user.setMobile(mobile);
		user.setGender(gender);
		user.setIsEmailVerifiyed(emailVerified);
		user.setIsMobileVerifiyed(mobileVerified);
		userDao.saveOrUpdateUser(user);
		
	}

	@Override
	public User getUserByUSerID(int userID) {
		return userDao.getUserByUserID(userID);
	}

	@Override
	public void editUser(String editName, String editUserName, String editMail, int editEmailVerified,
			String editMobile, int editMobileVerified, int editGender, int editRole, int userID) {
		User user = userDao.getUserByUserID(userID);
		user.setUserId(userID);
		user.setUsername(editUserName);
		user.setName(editName);
		user.setEmail(editMail);
		user.setIsEmailVerifiyed(editEmailVerified);
		user.setMobile(editMobile);
		user.setIsMobileVerifiyed(editMobileVerified);
		user.setGender(editGender);
		user.setRole(editRole);
		userDao.saveOrUpdateUser(user);
	}

	@Override
	public boolean userNameExist(String userName) {
		return userDao.userNameExist(userName);
	}

	@Override
	public boolean emailExist(String email) {
		return userDao.emailExist(email);
	}

	@Override
	public boolean mobileExist(String mobile) {
		return userDao.mobileExist(mobile);
	}

	@Override
	public void changeUserStatus(int status, int userID) {
		if (status == 0) {
			status = 1;
		}else if (status == 1) {
			status = 0;
		}
		
		User user = userDao.getUserByUserID(userID);
		user.setIsActive(status);
		
		userDao.saveOrUpdateUser(user);
		
	}

	@Override
	public boolean deleteUser(int userID) {
		
		User user = userDao.getUserByUserID(userID);
		user.setIsDeleted(Constant.DELETED_TRUE);
		
		userDao.saveOrUpdateUser(user);
		
		return true;
	}

	@Override
	public boolean emailExistForForgotPassword(String email) {
		return userDao.emailExistForForgotPassword(email);
	}

	@Override
	public User getUserByUSerEmail(String email) {
		return userDao.getUserByUserEmail(email);
		
	}

	@Override
	public void saveOrUpdateUser(User user) {
		userDao.saveOrUpdateUser(user);
	}

	@Override
	public List<User> getVendorList() {
		// TODO Auto-generated method stub
		return userDao.getVendorList();
	}
	
}
