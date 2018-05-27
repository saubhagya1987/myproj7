package com.airtel.kyc.repository.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.airtel.kyc.repository.entity.BaseEntity;

public class AuditedEntityListener {

	@PrePersist
	public void setNewAuditInfo(BaseEntity baseEntity) {

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		baseEntity.setCreatedOn(timestamp);
		baseEntity.setStatusFlag(1);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String userName = auth.getName();
			baseEntity.setCreatedBy(userName.toLowerCase());
		}
		baseEntity.setDeleteFlag(0);

	}

	@PreUpdate
	public void setUpdateAuditInfo(BaseEntity baseEntity) {

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		baseEntity.setUpdatedOn(timestamp);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			String userName = auth.getName();
			baseEntity.setUpdatedBy(userName);
		}
	}
}