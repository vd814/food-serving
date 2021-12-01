package com.swing.foodserving.dao;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.DataTableEntityConstant;
import com.swing.foodserving.constant.EntitySchema;
import com.swing.foodserving.entity.User;

@Repository
@EnableTransactionManagement
@Transactional
public class UserDaoImpl implements UserDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateUser(User user) {
		Session session = getSession();
		session.saveOrUpdate(user);
	}
	
	@Override
	public User findByUserName(String email) {
		Criteria cr = sessionFactory.getCurrentSession().createCriteria(User.class);
		cr.add(Restrictions.eq("username",email));
		
		List<User> users=cr.list();
		if (users.size() > 0) {
			User u=users.get(0);
			return u;
		} else {
			return null;
		}

	}

	@Override
	public List getAllUserWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		int count = ((Long)getSession().createQuery("select count(*) from User where is_deleted="+Constant.DELETED_FALSE+"").uniqueResult()).intValue();
		hs.setAttribute("numbrOfRecord", count);
		Session session = sessionFactory.getCurrentSession();
		String query="SELECT user,user.name From User user  where ";
		if(!searchValue.equals("")){
			query+=" user.username like '%"+searchValue+"%' or user.name like '%"+searchValue+"%' and ";
		}
		query+=" user.isDeleted="+Constant.DELETED_FALSE+" order by "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.USER, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}

	@Override
	public User getUserByUserID(int userID) {
		String query = "FROM User WHERE userId = "+userID;
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery(query).list();
		if (userList.size() >= 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public boolean userNameExist(String userName) {
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery("FROM User WHERE username=?").setParameter(0, userName).list();
		if (userList.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean emailExist(String email) {
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery("FROM User WHERE email=?").setParameter(0, email).list();
		if (userList.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean mobileExist(String mobile) {
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery("FROM User WHERE mobile=?").setParameter(0, mobile).list();
		if (userList.size() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean emailExistForForgotPassword(String email) {
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery("FROM User WHERE email=?").setParameter(0, email).list();
		if (userList.size() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByUserEmail(String email) {
		String query = "FROM User WHERE email = '"+email+"'";
		List<User> userList = new ArrayList<User>();
		userList = sessionFactory.getCurrentSession().createQuery(query).list();
		if (userList.size() >= 0) {
			return userList.get(0);
		}
		return null;
	}

	@Override
	public List<User> getVendorList() {
		String query = "FROM User WHERE role = "+Constant.ROLE_VENDOR+" "
				+ "AND isActive = "+Constant.ACTIVE+" "
				+ "AND isDeleted = "+Constant.DELETED_FALSE;
		return sessionFactory.getCurrentSession().createQuery(query).list();
	}

	
}
