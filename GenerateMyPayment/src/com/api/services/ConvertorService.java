package com.api.services;

import com.db.datatypes.NlpResult;
import com.db.datatypes.Pain001;

public class ConvertorService {
	public Pain001 convertTextToPayment(String text) {
		NlpResult nlpObject = textToNlpResult(text);
		Pain001 pain001 = NlpResultToPain001(nlpObject);
		
		return pain001;
	}
	
	public NlpResult textToNlpResult(String text) {
		return null;
	}
	
	public Pain001 NlpResultToPain001(NlpResult nlpObject) {
		return null;
	}
	
}
