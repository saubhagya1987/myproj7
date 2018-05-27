package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.TemplatesDao;
import com.airtel.kyc.repository.entity.Templates;

@Repository
public class TemplatesDaoImpl extends GenericDaoImpl<Templates, Integer> implements TemplatesDao{

	@Override
	public Templates getTemplateObj(String templateName, String locale, String chanel) {
		Map<String, Object> map = new HashMap<>();
		map.put("templateName", templateName);
		map.put("locale", locale);
		map.put("chanel", chanel);
		return findSingleResultByNamedQuery("Templates.findByTemplateNameChannelAndLocale", map);		
		
	}

}
