package com.airtel.kyc.repository.entity.task;

import java.io.Serializable;
import java.sql.Timestamp;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.entity.BaseEntity;
//import org.hibernate.annotations.Cascade;
//import org.codehaus.jackson.annotate.JsonManagedReference;
/**
 * The Class Customers.
 * 
/**
 * @author A1FB7X11
 * 
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "agent_log")
public class AgentLog extends BaseEntity implements Serializable {

	@Id
	@Column(name = "run_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "agent_log_seq")
	@SequenceGenerator(allocationSize=1,name = "agent_log_seq", sequenceName = "agent_log_seq")
	private Long run_id;
	
	@Column(name = "agent_id")
	private Integer agent_id;
	
	@Column(name = "agent_started_at")
	private Timestamp created_on;
	
	@Column(name = "agent_end_at")
	private Timestamp updated_on;
	
	@Column(name = "run_status")
	private String run_status;
	
	@Column(name = "run_info")
	private String run_info;

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public Timestamp getCreated_on() {
		return created_on;
	}

	public void setCreated_on(Timestamp created_on) {
		this.created_on = created_on;
	}

	public Timestamp getUpdated_on() {
		return updated_on;
	}

	public void setUpdated_on(Timestamp updated_on) {
		this.updated_on = updated_on;
	}

	public String getRun_status() {
		return run_status;
	}

	public void setRun_status(String run_status) {
		this.run_status = run_status;
	}

	public String getRun_info() {
		return run_info;
	}

	public void setRun_info(String run_info) {
		this.run_info = run_info;
	}

	public Long getRun_id() {
		return run_id;
	}

	public void setRun_id(Long run_id) {
		this.run_id = run_id;
	}

	@Override
	public String toString() {
		return "AgentLog [run_id=" + run_id + ", agent_id=" + agent_id
				+ ", created_on=" + created_on + ", updated_on=" + updated_on
				+ ", run_status=" + run_status + ", run_info=" + run_info + "]";
	}

}
