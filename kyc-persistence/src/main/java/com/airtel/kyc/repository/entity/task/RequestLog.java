package com.airtel.kyc.repository.entity.task;

import java.io.Serializable;
import java.sql.Timestamp;

//import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.airtel.kyc.repository.entity.BaseEntity;
import com.airtel.kyc.repository.listener.AuditedEntityListener;


@Entity
@Table(name = "request_log")

@NamedQueries({
	@NamedQuery(name = "RequestLog.findByReqId", query = " Select r from RequestLog r where request_id=:request_id")
	

})
public class RequestLog extends BaseEntity implements Serializable {

	@Id
	@Column(name = "request_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "request_log_seq")
	@SequenceGenerator(allocationSize = 1, name = "request_log_seq", sequenceName = "request_log_seq")
	private Long request_id;

	@Column(name = "run_id")
	private Long run_id;

	@Column(name = "agent_id")
	private Integer agent_id;

	@Column(name = "agent_name")
	private String agentName;

	@Column(name = "kyc_id")
	private String kyc_id;

	@Column(name = "number_id")
	private Long number_id;

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public Long getNumber_id() {
		return number_id;
	}

	public void setNumber_id(Long number_id) {
		this.number_id = number_id;
	}

	@Column(name = "req_created_on")
	private Timestamp req_created_on;

	@Column(name = "req_updated_on")
	private Timestamp req_updated_on;

	@Column(name = "req_raised_at")
	private Timestamp req_raised_at;

	@Column(name = "req_completd_at")
	private Timestamp req_completd_at;

	@Column(name = "req_due_time")
	private Timestamp req_due_time;

	@Column(name = "request_data", length = 3999)
	private String request_data;

	@Column(name = "error_data" , length = 3999)
	private String error_data;

	@Column(name = "response_data" , length = 3999)
	private String response_data;

	@Column(name = "request_sr_id")
	private String request_sr_id;

	@Column(name = "request_status")
	private String request_status;

	@Column(name = "retry_count")
	private Integer retry_count = 0;

	public String getRequest_by() {
		return request_by;
	}

	public void setRequest_by(String request_by) {
		this.request_by = request_by;
	}

	@Column(name = "REQUEST_BY")
	private String request_by;

	public Integer getRetry_count() {
		return retry_count;
	}

	public void setRetry_count(Integer retry_count) {
		this.retry_count = retry_count;
	}

	public Long getRequest_id() {
		return request_id;
	}

	public void setRequest_id(Long request_id) {
		this.request_id = request_id;
	}

	public Long getRun_id() {
		return run_id;
	}

	public void setRun_id(Long run_id) {
		this.run_id = run_id;
	}

	public Integer getAgent_id() {
		return agent_id;
	}

	public void setAgent_id(Integer agent_id) {
		this.agent_id = agent_id;
	}

	public String getKyc_id() {
		return kyc_id;
	}

	public void setKyc_id(String kyc_id) {
		this.kyc_id = kyc_id;
	}

	public Timestamp getReq_created_on() {
		return req_created_on;
	}

	public void setReq_created_on(Timestamp req_created_on) {
		this.req_created_on = req_created_on;
	}

	public Timestamp getReq_updated_on() {
		return req_updated_on;
	}

	public void setReq_updated_on(Timestamp req_updated_on) {
		this.req_updated_on = req_updated_on;
	}

	public Timestamp getReq_raised_at() {
		return req_raised_at;
	}

	public void setReq_raised_at(Timestamp req_raised_at) {
		this.req_raised_at = req_raised_at;
	}

	public Timestamp getReq_completd_at() {
		return req_completd_at;
	}

	public void setReq_completd_at(Timestamp req_completd_at) {
		this.req_completd_at = req_completd_at;
	}

	public String getRequest_data() {
		return request_data;
	}

	public void setRequest_data(String request_data) {
		this.request_data = request_data;
	}

	public String getResponse_data() {
		return response_data;
	}

	public void setResponse_data(String response_data) {
		this.response_data = response_data;
	}

	public String getRequest_status() {
		return request_status;
	}

	public void setRequest_status(String request_status) {
		this.request_status = request_status;
	}

	public Timestamp getReq_due_time() {
		return req_due_time;
	}

	public void setReq_due_time(Timestamp req_due_time) {
		this.req_due_time = req_due_time;
	}

	public String getRequest_sr_id() {
		return request_sr_id;
	}

	public void setRequest_sr_id(String request_sr_id) {
		this.request_sr_id = request_sr_id;
	}

	public String getError_data() {
		return error_data;
	}

	public void setError_data(String error_data) {
		this.error_data = error_data;
	}

}
