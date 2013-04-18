package com.fhi.journal.service;

import java.util.List;

import com.fhi.framework.page.Condition;
import com.fhi.journal.vo.FhiJournalVote;

public interface JournalIn {	
	public List query(Condition condition);

	public List query(String hqlString);

	public Object load(Class obj, String obj_id);
	
	public boolean update(Object obj);
	
	public boolean save(Object obj);
	
	/**
	 * 计算当前票数
	 * @param code
	 * @return
	 */
	public FhiJournalVote getCount(String code,String userId);
}
