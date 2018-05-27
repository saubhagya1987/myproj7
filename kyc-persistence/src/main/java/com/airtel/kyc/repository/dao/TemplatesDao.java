package com.airtel.kyc.repository.dao;

import com.airtel.kyc.repository.entity.Templates;

public interface TemplatesDao extends GenericDao<Templates, Integer>{
	
	Templates getTemplateObj(String templateName,String locale,String chanel);
	
	

}
