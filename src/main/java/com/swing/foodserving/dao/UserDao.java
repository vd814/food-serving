package com.swing.foodserving.dao;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;

import com.swing.foodserving.entity.User;


public interface UserDao {
	public User findByUserName(String email);
	public void saveOrUpdateUser(User user); 
	public List getAllUserWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public User getUserByUserID(int userID);
	public User getUserByUserEmail(String email);
	public boolean userNameExist(String userName);
	public boolean emailExist(String email);
	public boolean mobileExist(String mobile);
	public boolean emailExistForForgotPassword(String email);
	public List<User> getVendorList();
	
}
