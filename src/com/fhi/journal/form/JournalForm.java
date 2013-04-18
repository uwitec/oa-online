package com.fhi.journal.form;


import org.apache.struts.action.ActionForm;

import com.fhi.journal.vo.FhiJournalVote;


public class JournalForm extends ActionForm {
	
	private FhiJournalVote vote=null;

	public FhiJournalVote getVote() {
		if(vote==null){
			vote = new FhiJournalVote();
		}
		return vote;
	}

	public void setVote(FhiJournalVote vote) {
		this.vote = vote;
	}
}
