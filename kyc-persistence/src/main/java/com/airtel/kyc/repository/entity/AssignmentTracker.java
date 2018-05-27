package com.airtel.kyc.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "assignment_tracker")
@NamedQueries({
	@NamedQuery(name = "AssignmentTracker.findNextAssignment", query = " Select at from AssignmentTracker at")
})

public class AssignmentTracker implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "assignment_tracker_id")
	private Integer assignmenTrackerId;
	
	@Column(name = "current_assign", nullable = false)
	private Integer currentAssign;

	@Column(name = "next_assign", nullable = false)
	private Integer nextAssign;

	@Column(name = "max_sequence")
	private Integer maxSequence;

	public Integer getAssignmenTrackerId() {
		return assignmenTrackerId;
	}

	public void setAssignmenTrackerId(Integer assignmenTrackerId) {
		this.assignmenTrackerId = assignmenTrackerId;
	}

	public Integer getCurrentAssign() {
		return currentAssign;
	}

	public void setCurrentAssign(Integer currentAssign) {
		this.currentAssign = currentAssign;
	}

	public Integer getNextAssign() {
		return nextAssign;
	}

	public void setNextAssign(Integer nextAssign) {
		this.nextAssign = nextAssign;
	}

	public Integer getMaxSequence() {
		return maxSequence;
	}

	public void setMaxSequence(Integer maxSequence) {
		this.maxSequence = maxSequence;
	}
}