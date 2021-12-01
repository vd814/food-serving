package com.swing.foodserving.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.MessageConstant;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.service.UserService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping(value = "/u/admin")
public class AdminUserController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/userList", method = RequestMethod.GET)
	public String userList() {
		return "../admin/userList";
	}
	
	@RequestMapping(value = "/getAllUserWithDatatable",method = RequestMethod.GET)
	@ResponseBody
	public JSONObject getAllUserWithDatatable(HttpServletRequest req) {
		HttpSession hs=req.getSession(false);
		String searchValue=req.getParameter("search[value]");
		String sortingValue=req.getParameter("order[0][dir]");
		String columeNumber=req.getParameter("order[0][column]");
		String draw=req.getParameter("draw");
		String length=req.getParameter("length");
		String start=req.getParameter("start");
		int paginationStart=Integer.parseInt(start);
		int paginationEnd=Integer.parseInt(length);
		List<Object[]> allUser = userService.getAllUserWithDataTable(paginationStart, paginationEnd, hs, searchValue, sortingValue, columeNumber);
		
		JSONObject jo=new JSONObject();
		StringBuffer sb=new StringBuffer();
		sb.append("[");
		int index = paginationStart + 1;
		for (Object[] objects : allUser) {
			User user = (User)objects[0];
			String email = user.getEmail();
			String userName = user.getUsername();
			String role = "";
			if (user.getRole() == Constant.ROLE_ADMIN) {
				role = "ADMIN";
			}else if (user.getRole() == Constant.ROLE_VENDOR) {
				role = "VENDOR";
			}else {
				role = "USER";
			}
			String name = "";
			if (user.getName() != null) {
				name = user.getName();
			}else {
				name = "----------";
			}
			String mobile = "";
			if (user.getMobile() != null) {
				mobile = user.getMobile();
			}else {
				mobile = "----------";
			}
			int activeStatus = user.getIsActive();
			String isactive = "";
			if(activeStatus == 1){
				isactive = "<input type='checkbox' checked class='js-switch'  data-size='small'  data-color='#1bb99a' onchange='changeActiveStatus("+activeStatus+","+user.getUserId()+")'>";
			}else{
				isactive = "<input type='checkbox'  class='js-switch'  data-size='small'  data-color='#1bb99a' onchange='changeActiveStatus("+activeStatus+","+user.getUserId()+")'>";
			}
			String setting = "<a href='javascript:void(0);' class='action-icon'><i onclick='editUser("+user.getUserId()+")' class='mdi mdi-square-edit-outline'></i>" + 
					"</a> <a onclick='deleteUser("+user.getUserId()+")' href='javascript:void(0);' class='action-icon'> "+
					"<i class='mdi mdi-delete'></i> </a>";
			
			sb.append("[\""+index+"\",\""+name+"\",\""+userName+"\",\""+email+"\",\""+mobile+"\",\""+role+"\",\""+isactive+"\",\""+setting+"\",],");
			index++;
		}
		String dt=sb.toString();
		if(allUser.size()!=0){
			dt=dt.substring(0, dt.lastIndexOf(","))+"]";
		}else{
			dt+="]";
		}
		jo.put("draw", Integer.parseInt(draw));
		jo.put("recordsTotal", (Integer)hs.getAttribute("numbrOfRecord"));
		jo.put("recordsFiltered", (Integer)hs.getAttribute("numbrOfRecord"));
		jo.put("data", dt);
		return jo;
		
	}
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public String addUser(@RequestParam String name,
			@RequestParam String userName,
			@RequestParam String password,
			@RequestParam String mail,
			@RequestParam int emailVerified,
			@RequestParam String mobile,
			@RequestParam int mobileVerified,
			@RequestParam int gender,
			@RequestParam int role,
			RedirectAttributes redir) {
		userService.addUser(name, userName, password, mail, mobile, gender, role,mobileVerified,emailVerified);
		
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.USER_ADD);
		return "redirect:userList";
	}
	
	@RequestMapping(value = "/getUserByUserID", method = RequestMethod.POST)
	@ResponseBody
	public User getUserByUserID(@RequestParam int userID) {
		return userService.getUserByUSerID(userID);
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@RequestParam String editName,
			@RequestParam String editUserName,
			@RequestParam String editMail,
			@RequestParam int editEmailVerified,
			@RequestParam String editMobile,
			@RequestParam int editMobileVerified,
			@RequestParam int editGender,
			@RequestParam int editRole,
			@RequestParam int userID,
			RedirectAttributes redir) {
		userService.editUser(editName, editUserName, editMail, editEmailVerified, editMobile, editMobileVerified, editGender, editRole, userID);
		redir.addFlashAttribute("msgStatus",MessageConstant.MSG_STATUS_SUCCESS);
		redir.addFlashAttribute("msg",MessageConstant.USER_EDIT);
		return "redirect:userList";
	}
	
	@RequestMapping(value = "/userNameExist", method = RequestMethod.POST)
	@ResponseBody
	public boolean userNameExist(@RequestParam String userName,@RequestParam String currentUserName) {
		if (userName.equals(currentUserName)) {
			return true;
		}
		return userService.userNameExist(userName);
	}
	
	@RequestMapping(value = "/emailExist", method = RequestMethod.POST)
	@ResponseBody
	public boolean emailExist(@RequestParam String email,@RequestParam String currentEmail) {
		if (email.equals(currentEmail)) {
			return true;
		}
		return userService.emailExist(email);
	}
	
	@RequestMapping(value = "/mobileExist", method = RequestMethod.POST)
	@ResponseBody
	public boolean mobileExist(@RequestParam String mobile,@RequestParam String currentMobile) {
		if (mobile.equals(currentMobile)) {
			return true;
		}
		return userService.mobileExist(mobile);
	}
	
	@RequestMapping(value = "/changeUserStatus",method = RequestMethod.POST)
	@ResponseBody
	public String changeUserStatus(@RequestParam int status,@RequestParam int userID) {
		userService.changeUserStatus(status, userID);
		return MessageConstant.STATUS_CHANGED;
	}
	
	@RequestMapping(value = "deleteUser")
	@ResponseBody
	public boolean deleteUser(@RequestParam int userID) {
		return userService.deleteUser(userID);
	}
}
