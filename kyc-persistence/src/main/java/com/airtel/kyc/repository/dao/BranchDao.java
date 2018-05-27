package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Branch;

public interface BranchDao extends GenericDao<Branch, Integer>{

	List<Branch> findBranchList(int zoneId);

}
