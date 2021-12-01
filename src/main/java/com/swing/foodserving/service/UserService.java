package com.swing.foodserving.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestParam;

import com.swing.foodserving.entity.User;


public interface UserService {
	
	public List getAllUserWithDataTable(int paginationStart,int paginationEnd,HttpSession hs,String searchValue,String sortingValue,String columeNumber);
	public void registerUser(int role,String email,String userName,String password);
	public void addUser(String name, String userName, String password, String mail, String mobile, int gender, int role, int mobileVerified,int emailVerified);
	public User getUserByUSerID(int userID);
	public User getUserByUSerEmail(String email);
	public void editUser(String editName, String editUserName, String editMail, int editEmailVerified, String editMobile, int editMobileVerified, int editGender, int editRole, int userID);
	public boolean userNameExist(String userName);
	public boolean emailExist(String email);
	public boolean mobileExist(String mobile);
	public void changeUserStatus(int status, int userID);
	public boolean deleteUser(int userID);
	public boolean emailExistForForgotPassword(String email);
	public void saveOrUpdateUser(User user);
	public List<User> getVendorList();
}

