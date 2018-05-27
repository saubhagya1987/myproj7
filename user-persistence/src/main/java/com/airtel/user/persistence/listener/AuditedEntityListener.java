package com.airtel.user.persistence.listener;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.airtel.user.persistence.entities.BaseEntity;

public class AuditedEntityListener {

	@PrePersist
	public void setNewAuditInfo(BaseEntity baseEntity) {

		Date date = new Date();
		Timestamp timestamp = new Timestamp(date.getTime());

		baseEntity.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		baseEntity.setStatusFlag(1);
		String userName = baseEntity.getCreatedBy();
		baseEntity.setCreatedBy(userName);
		
		/*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			userName = auth.getName();
			baseEntity.setCreatedBy(userName);
		}*/
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