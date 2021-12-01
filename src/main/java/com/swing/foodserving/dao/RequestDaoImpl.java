package com.swing.foodserving.dao;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.swing.foodserving.constant.Constant;
import com.swing.foodserving.constant.DataTableEntityConstant;
import com.swing.foodserving.constant.EntitySchema;
import com.swing.foodserving.entity.Request;
import com.swing.foodserving.entity.RequestCancel;
import com.swing.foodserving.utility.UserUtil;

@Repository
@EnableTransactionManagement
@Transactional
public class RequestDaoImpl implements RequestDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}
	
	@Override
	public void saveOrUpdateRequest(Request request) {
		sessionFactory.getCurrentSession().getSession().saveOrUpdate(request);
	}

	@Override
	public List getAllRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		int count = ((Long)getSession().createQuery("select count(*) from Request").uniqueResult()).intValue();
		hs.setAttribute("numbrOfRecord", count);
		Session session = sessionFactory.getCurrentSession();
		String query="SELECT re From Request re  "
				+ "LEFT JOIN User u ON u.userId = re.userID ";
		if(!searchValue.equals("")){
			query+="WHERE u.name LIKE '%"+searchValue+"%'";
		}
		query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_ADMIN, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}


	@Override
	public Request getRequestByID(int requestID) {
		String query = "FROM Request WHERE rID = "+requestID;
		return (Request) getSession().createQuery(query).getSingleResult();
	}

	@Override
	public List getVendorRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber, int userID) {
		int count = ((Long)getSession().createQuery("select count(*) from Request re where re.vendorID = "+userID).uniqueResult()).intValue();
		hs.setAttribute("numbrOfRecord", count);
		Session session = sessionFactory.getCurrentSession();
		String query="SELECT re From Request re  "
				+ "LEFT JOIN User u ON u.userId = re.vendorID "
				+ "WHERE re.vendorID = "+userID+" ";
		if(!searchValue.equals("")){
			query+="AND u.name LIKE '%"+searchValue+"%'";
		}
		query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_VENDOR, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}

	@Override
	public List getUserRequestWithDataTable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber, int userID) {
		int count = ((Long)getSession().createQuery("select count(*) from Request re where re.userID = "+userID).uniqueResult()).intValue();
		hs.setAttribute("numbrOfRecord", count);
		Session session = sessionFactory.getCurrentSession();
		String query="SELECT re From Request re  "
				+ "LEFT JOIN User u ON u.userId = re.userID "
				+ "WHERE re.userID = "+userID+" ";
		if(!searchValue.equals("")){
			query+="AND u.name LIKE '%"+searchValue+"%'";
		}
		query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_USER, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}

	

	
	
}
