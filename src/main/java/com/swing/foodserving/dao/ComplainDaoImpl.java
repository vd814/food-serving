package com.swing.foodserving.dao;

import java.util.ArrayList;
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
import com.swing.foodserving.entity.RequestComplain;
import com.swing.foodserving.entity.RequestReviewAndRating;
import com.swing.foodserving.utility.UserUtil;

@Repository
@EnableTransactionManagement
@Transactional
public class ComplainDaoImpl implements ComplainDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveOrUpdateComplain(RequestComplain rc) {
		getSession().saveOrUpdate(rc);
	}

	@Override
	public List getAllComplainWithDatatable(int paginationStart, int paginationEnd, HttpSession hs, String searchValue,
			String sortingValue, String columeNumber) {
		Session session = sessionFactory.getCurrentSession();
		String query;
		if (UserUtil.getUser(hs).getRole() == Constant.ROLE_ADMIN) {
			query="SELECT rc,r,u,v From RequestComplain rc "
					+ "LEFT JOIN Request r ON rc.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.userID "
					+ "LEFT JOIN User v ON v.userId = r.vendorID ";
			if(!searchValue.equals("")){
				query+="WHERE ( u.name LIKE '%"+searchValue+"%' OR v.name LIKE '%"+searchValue+"%' ) ";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.COMPLAIN, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else if (UserUtil.getUser(hs).getRole() == Constant.ROLE_VENDOR) {
			query="SELECT rc,r,u From RequestComplain rc "
					+ "LEFT JOIN Request r ON rc.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.userID "
					+ "WHERE r.vendorID = " + UserUtil.getUser(hs).getUserId() + " ";
			if(!searchValue.equals("")){
				query+="AND u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.COMPLAIN, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else {
			query="SELECT rc,r,u From RequestComplain rc "
					+ "LEFT JOIN Request r ON rc.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.vendorID "
					+ "WHERE r.userID = " + UserUtil.getUser(hs).getUserId() + " ";
			if(!searchValue.equals("")){
				query+="AND u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.COMPLAIN, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}
		hs.setAttribute("numbrOfRecord", session.createQuery(query).getResultList().size());
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}

	@Override
	public RequestComplain getComplainByID(int complainID) {
		String query = "FROM RequestComplain WHERE id = "+complainID;
		List<RequestComplain> complainList = new ArrayList<RequestComplain>();
		complainList = sessionFactory.getCurrentSession().createQuery(query).list();
		if (complainList.size() >= 0) {
			return complainList.get(0);
		}
		return null;
	}

	@Override
	public void deleteComplain(RequestComplain rc) {
		getSession().delete(rc);
	}
	
}
