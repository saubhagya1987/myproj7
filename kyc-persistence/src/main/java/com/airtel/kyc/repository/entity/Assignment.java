package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "assignment")

public class Assignment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "assignment_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "assignment_seq")
	@SequenceGenerator(allocationSize = 1,name = "assignment_seq", sequenceName = "assignment_seq")
	private Integer assignmentId;
	
	@Column(name = "user_id", nullable = false)
	private Integer userId;
	
	@Column(name = "sequence")
	private Integer sequence;

	@Column(name = "count")
	private Integer count;

	@Column(name = "next_assign_sequence")
	private Integer nextAssignSequence;
	
	@Column(name = "next_assign_user_id")
	private Integer nextAssignUserId;

	public Integer getAssignmentId() {
		return assignmentId;
	}

	public void setAssignmentId(Integer assignmentId) {
		this.assignmentId = assignmentId;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		
		this.count = count;
	}

	public Integer getNextAssignSequence() {
		return nextAssignSequence;
	}

	public void setNextAssignSequence(Integer nextAssignSequence) {
		this.nextAssignSequence = nextAssignSequence;
	}

	public Integer getNextAssignUserId() {
		return nextAssignUserId;
	}

	public void setNextAssignUserId(Integer nextAssignUserId) {
		this.nextAssignUserId = nextAssignUserId;
	}
}