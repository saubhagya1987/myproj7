/*package com.airtel.kyc.integration.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airtel.kyc.helpers.dto.TestRequestDto;
import com.airtel.kyc.integration.service.TestService;
import com.airtel.kyc.repository.dao.AdminDao;
import com.airtel.kyc.repository.entity.Admin;

@Service
public class TestServiceImpl implements TestService{
	
	@Autowired
	AdminDao adminDao;

	@Override
	public void save(TestRequestDto requestDto) {
		Admin admin=new Admin();
		admin.setName(requestDto.getUserName());
		adminDao.add(admin);
	}

}
*/