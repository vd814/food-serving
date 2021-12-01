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
import com.swing.foodserving.entity.RequestDecline;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.utility.UserUtil;

@Repository
@EnableTransactionManagement
@Transactional
public class RequestDeclineDaoImpl implements RequestDeclineDao{
	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void addRequestDecline(RequestDecline requestDecline) {
		getSession().save(requestDecline);
	}
	
	@Override
	public void editRequestDecline(RequestDecline requestDecline) {
		getSession().saveOrUpdate(requestDecline);
	}

	@Override
	public RequestDecline getRequestDeclineByID(int requestDeclineID) {
		String query = "FROM RequestDecline WHERE id = "+requestDeclineID;
		List<RequestDecline> requestDeclineList = new ArrayList<RequestDecline>();
		requestDeclineList = sessionFactory.getCurrentSession().createQuery(query).list();
		if (requestDeclineList.size() >= 0) {
			return requestDeclineList.get(0);
		}
		return null;
	}

	@Override
	public List getAllDeclineRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber) {
		Session session = sessionFactory.getCurrentSession();
		String query;
		if (UserUtil.getUser(hs).getRole() == Constant.ROLE_ADMIN) {
			query="SELECT rd,v,r,u From RequestDecline rd "
					+ "LEFT JOIN User v ON v.userId = rd.userID "
					+ "LEFT JOIN Request r ON r.rID = rd.requestID "
					+ "LEFT JOIN User u ON u.userId = r.userID ";
			if(!searchValue.equals("")){
				query+="WHERE u.name LIKE '%"+searchValue+"%' OR v.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_DECLINE, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else if (UserUtil.getUser(hs).getRole() == Constant.ROLE_VENDOR) {
			query="SELECT rd,r,u From RequestDecline rd "
					+ "LEFT JOIN Request r ON r.rID = rd.requestID "
					+ "LEFT JOIN User u ON u.userId = r.userID "
					+ "WHERE rd.userID = " + UserUtil.getUser(hs).getUserId() + " ";
			if(!searchValue.equals("")){
				query+="u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_DECLINE, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else {
			query="SELECT rd,r,u From RequestDecline rd "
					+ "LEFT JOIN Request r ON r.rID = rd.requestID "
					+ "LEFT JOIN User u ON u.userId = rd.userID "
					+ "WHERE r.userID = " + UserUtil.getUser(hs).getUserId() + " ";
			if(!searchValue.equals("")){
				query+="u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REQUEST_DECLINE, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}
		hs.setAttribute("numbrOfRecord", session.createQuery(query).getResultList().size());
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}

	
}
