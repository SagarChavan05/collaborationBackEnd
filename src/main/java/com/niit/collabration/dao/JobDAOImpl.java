package com.niit.collabration.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.collabration.model.Job;
import com.niit.collabration.model.Job;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public JobDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Transactional
	public void saveOrUpdate(Job job) {

		System.out.println("hiii");

		sessionFactory.getCurrentSession().saveOrUpdate(job);

	}

	@Transactional
	public void delete(int jobId) {

		Job jobToDelete = new Job();
		jobToDelete.setJobId(jobId);
		sessionFactory.getCurrentSession().delete(jobToDelete);

	}

	@Transactional
	public Job get(int jobId) {
		String hql = "from Job where jobId=" + "'" + jobId + "'";

		Query query = sessionFactory.getCurrentSession().createQuery(hql);

		@SuppressWarnings("unchecked")
		List<Job> listJob = (List<Job>) query.list();

		if (listJob != null && !listJob.isEmpty()) {
			return listJob.get(0);
		}
		return null;
	}
	
	@Transactional
	public List<Job> list() {
		@SuppressWarnings("unchecked")
		List<Job> listJob = (List<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return listJob;
	}
	
}