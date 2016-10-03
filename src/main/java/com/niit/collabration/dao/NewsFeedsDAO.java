package com.niit.collabration.dao;

import java.util.List;

import com.niit.collabration.model.NewsFeeds;

public interface NewsFeedsDAO {

	public void saveOrUpdate(NewsFeeds newsFeeds);

	public void delete(int nfId);

	public NewsFeeds get(int nfId);
	
	public List<NewsFeeds> list();

}
