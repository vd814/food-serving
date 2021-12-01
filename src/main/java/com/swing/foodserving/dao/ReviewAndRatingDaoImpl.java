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
import com.swing.foodserving.entity.RequestReviewAndRating;
import com.swing.foodserving.entity.User;
import com.swing.foodserving.utility.UserUtil;

@Repository
@EnableTransactionManagement
@Transactional
public class ReviewAndRatingDaoImpl implements ReviewAndRatingDao{

	@Autowired
	SessionFactory sessionFactory;
	
	private Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public void saveReviewAndRating(RequestReviewAndRating rr) {
		getSession().saveOrUpdate(rr);
	}

	@Override
	public RequestReviewAndRating getReviewByID(int reviewID) {
		String query = "FROM RequestReviewAndRating WHERE id = "+reviewID;
		List<RequestReviewAndRating> reviewList = new ArrayList<RequestReviewAndRating>();
		reviewList = sessionFactory.getCurrentSession().createQuery(query).list();
		if (reviewList.size() >= 0) {
			return reviewList.get(0);
		}
		return null;
	}
	
	@Override
	public List getAllCompetedRequestWithDatatable(int paginationStart, int paginationEnd, HttpSession hs,
			String searchValue, String sortingValue, String columeNumber) {
		Session session = sessionFactory.getCurrentSession();
		String query;
		if (UserUtil.getUser(hs).getRole() == Constant.ROLE_ADMIN) {
			query="SELECT r,rr,u,v,rc From Request r "
					+ "LEFT JOIN RequestReviewAndRating rr ON rr.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.userID "
					+ "LEFT JOIN User v ON v.userId = r.vendorID "
					+ "LEFT JOIN RequestComplain rc ON rc.requestID = r.rID "
					+ "WHERE r.requestStatus = " + Constant.REQUEST_FOOD_DELIVERED +" ";
			if(!searchValue.equals("")){
				query+="AND ( u.name LIKE '%"+searchValue+"%' OR v.name LIKE '%"+searchValue+"%' )";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REVIEW_RATING, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else if (UserUtil.getUser(hs).getRole() == Constant.ROLE_VENDOR) {
			query="SELECT r,rr,u From Request r "
					+ "LEFT JOIN RequestReviewAndRating rr ON rr.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.userID "
					+ "WHERE r.vendorID = " + UserUtil.getUser(hs).getUserId() + " "
					+ "AND r.requestStatus = " + Constant.REQUEST_FOOD_DELIVERED +" ";
			if(!searchValue.equals("")){
				query+="AND u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REVIEW_RATING, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}else {
			query="SELECT r,rr,rc,u From Request r "
					+ "LEFT JOIN RequestReviewAndRating rr ON rr.requestID = r.rID "
					+ "LEFT JOIN RequestComplain rc ON rc.requestID = r.rID "
					+ "LEFT JOIN User u ON u.userId = r.vendorID "
					+ "WHERE r.userID = " + UserUtil.getUser(hs).getUserId() + " "
					+ "AND r.requestStatus = " + Constant.REQUEST_FOOD_DELIVERED +" ";
			if(!searchValue.equals("")){
				query+="AND u.name LIKE '%"+searchValue+"%'";
			}
			query+="ORDER BY "+EntitySchema.getSortingcolumnName(DataTableEntityConstant.REVIEW_RATING, Integer.parseInt(columeNumber))+" "+sortingValue+"";
		}
		hs.setAttribute("numbrOfRecord", session.createQuery(query).getResultList().size());
		return session.createQuery(query).setFirstResult(paginationStart).setMaxResults(paginationEnd).list();
	}
	
	
}
