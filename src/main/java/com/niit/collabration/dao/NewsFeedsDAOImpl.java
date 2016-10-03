package com.niit.collabration.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabration.model.NewsFeeds;
import com.niit.collabration.model.NewsFeeds;

@Repository("newsFeedsDAO")
public class NewsFeedsDAOImpl implements NewsFeedsDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public NewsFeedsDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(NewsFeeds newsFeeds) {

		System.out.println("hiii");

		sessionFactory.getCurrentSession().saveOrUpdate(newsFeeds);

	}

	@Transactional
	public void delete(int nfId) {

		NewsFeeds newsFeedsToDelete = new NewsFeeds();
		newsFeedsToDelete.setNfId(nfId);
		sessionFactory.getCurrentSession().delete(newsFeedsToDelete);

	}

	@Transactional
	public NewsFeeds get(int nfId) {
		String hql = "from NewsFeeds where nfId=" + "'" + nfId + "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<NewsFeeds> listNewsFeeds = (List<NewsFeeds>) query.list();

		if (listNewsFeeds != null && !listNewsFeeds.isEmpty()) {
			return listNewsFeeds.get(0);
		}
		return null;
	}

	@Transactional
	public List<NewsFeeds> list() {
		@SuppressWarnings("unchecked")
		List<NewsFeeds> listNewsFeeds = (List<NewsFeeds>) sessionFactory.getCurrentSession()
				.createCriteria(NewsFeeds.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listNewsFeeds;
	}

}