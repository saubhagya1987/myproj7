package com.airtel.kyc.repository.dao;

import java.util.List;

import com.airtel.kyc.repository.entity.Cluster_;

public interface ClusterDao extends GenericDao<Cluster_, Integer>{

	List<Cluster_> findClusterList(int branchId);

}
