package com.airtel.kyc.repository.daoimpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.DateType;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.airtel.kyc.helpers.dto.ReportRequestDto;
import com.airtel.kyc.helpers.dto.ReportResponseDto;
import com.airtel.kyc.repository.dao.ReportDao;
import com.airtel.kyc.utils.DateUtils;
import com.googlecode.genericdao.dao.hibernate.HibernateBaseDAO;

@Repository
public class ReportDaoImpl extends HibernateBaseDAO implements ReportDao {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	DataSource dataSource;

	@SuppressWarnings("rawtypes")
	@Override
	public ReportResponseDto getTotalCountOfSubscriber(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT count(*) totalCount, "
				+ "count(case when sd.final_status='APPROVED' then 1 end) approvedCount, "
				+ "count(case when sd.final_status='BARRED' then 1  end) barredCount, "
				+ "count(case when sd.final_status='REJECTED'  then 1  end) rejectedCount, "
				+ "count(case when sd.HOTLINE_FLAG=1  then 1  end) hotLineCount, "
				+ "count(case when sd.CASE_TYPE='EDIT' and (sd.updated_on = s.updated_on) then 1  end) editCount "
				+ "FROM SUBSCRIBER S "
				+ "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID and IS_OLD_USER_DETAILS='0' "
				+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'" + "and S.created_by ='"
				+ createdBy + "'");
		Session session = sessionFactory.openSession();

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("totalCount", IntegerType.INSTANCE)
				.addScalar("approvedCount", IntegerType.INSTANCE).addScalar("barredCount", IntegerType.INSTANCE)
				.addScalar("rejectedCount", IntegerType.INSTANCE).addScalar("hotLineCount", IntegerType.INSTANCE)
				.addScalar("editCount", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (ReportResponseDto) sqlQueryObject.uniqueResult();

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReportResponseDto> channelPartnerWiseSubReport(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String status = reportRequestDto.getStatus();
		String createdBy = reportRequestDto.getLoggedInUserName();
		if (status.equals("HOTLINE")) {
			Integer hotlineStatus = 1;
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"select S.MSISDN as msisdn,SD.FIRST_NAME as firstName,SD.LAST_NAME as lastName,SD.FINAL_STATUS_REASON "
							+ "FROM SUBSCRIBER S "
							+ "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID "
							+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'"
							+ " and IS_OLD_USER_DETAILS=0  and sd.HOTLINE_FLAG='" + hotlineStatus + "'"
							+ " and S.created_by ='" + createdBy + "'");
			Session session = sessionFactory.openSession();
			Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("msisdn", StringType.INSTANCE)
					.addScalar("firstName", StringType.INSTANCE).addScalar("lastName", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
			return (List<ReportResponseDto>) sqlQueryObject.list();
		} else {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"select   S.MSISDN as msisdn,SD.FIRST_NAME as firstName,SD.LAST_NAME as lastName,SD.FINAL_STATUS_REASON as reason "
							+ "FROM SUBSCRIBER S "
							+ "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID "
							+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'");
							sqlQuery.append(" and ");
							if(status.equals("PENDING")){
								sqlQuery.append("((IS_OLD_USER_DETAILS=0 and sd.final_status='" + status + "') or (IS_OLD_USER_DETAILS=1 and S.UPDATED_ON=SD.UPDATED_ON and sd.final_status='" + status + "'))");
							}else{
								sqlQuery.append("IS_OLD_USER_DETAILS=0  and sd.final_status='" + status + "'");
							}
							sqlQuery.append( " and S.created_by ='"
							+ createdBy + "'");
			Session session = sessionFactory.openSession();
			Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("msisdn", StringType.INSTANCE)
					.addScalar("firstName", StringType.INSTANCE).addScalar("lastName", StringType.INSTANCE)
					.addScalar("reason", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
			return (List<ReportResponseDto>) sqlQueryObject.list();
		}
	}

	@Override
	public ReportResponseDto getTotalCountOfDataExecutive(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT  " + "count(case when sd.case_type='NEW' then 1  end) newRegistration, "
				+ "count(case when sd.case_type='EXISTING' and sd.final_status='PENDING' then 1  end) existingCustomerNewLine, "
				+ "count((case when sd.case_type='NEW' then 1 "
				+ " when sd.case_type='EXISTING'  and sd.final_status='PENDING' then 1  end)) NEW_AND_EXISTING_COUNT, count(*) totalProcessed, "
				+ "count(case when sd.final_status='APPROVED'  then 1 end) totalApproved, "
				+ "count(case when sd.final_status='BARRED' then 1  end) totalBarred, "
				+ "count(case when sd.final_status='REJECTED'  then 1  end) totalRejected, "
				+ "count(case when sd.HOTLINE_FLAG=1  then 1  end) totalHotlined, "
				+ "count(case when sd.CASE_TYPE='EDIT' then 1  end) totalEditProfile, "
				+ "count(case when sd.CASE_TYPE='EDIT' and sd.final_status='APPROVED' then 1  end) totalEditProfileApproved, "
				+ "count(case when sd.CASE_TYPE='EDIT' and sd.final_status='REJECTED' then 1  end) totalEditProfileRejected "
				+ "FROM SUBSCRIBER S " + "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID "
				+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'" + "and S.created_by ='"
				+ createdBy + "' and IS_OLD_USER_DETAILS='0'");
		Session session = sessionFactory.openSession();

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
				.addScalar("newRegistration", IntegerType.INSTANCE)
				.addScalar("existingCustomerNewLine", IntegerType.INSTANCE)
				.addScalar("totalProcessed", IntegerType.INSTANCE).addScalar("totalApproved", IntegerType.INSTANCE)
				.addScalar("totalBarred", IntegerType.INSTANCE).addScalar("totalRejected", IntegerType.INSTANCE)
				.addScalar("totalHotlined", IntegerType.INSTANCE).addScalar("totalEditProfile", IntegerType.INSTANCE)
				.addScalar("totalEditProfileApproved", IntegerType.INSTANCE)
				.addScalar("totalEditProfileRejected", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (ReportResponseDto) sqlQueryObject.uniqueResult();
	}

	@Override
	public List<ReportResponseDto> dataExecutiveWiseSubReport(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String status = reportRequestDto.getStatus();
		String createdBy = reportRequestDto.getLoggedInUserName();
		if (status.equals("HOTLINE")) {
			Integer hotlineStatus = 1;
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"select sd.CASE_TYPE as caseType, sd.FIRST_NAME as firstName,sd.LAST_NAME as lastName,S.updated_by as data_executive,s.updated_on as execution_date,sd.final_status_reason bar_reason"
							+ "FROM SUBSCRIBER S "
							+ "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID "
							+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'"
							+ " and IS_OLD_USER_DETAILS=0  and sd.HOTLINE_FLAG='" + hotlineStatus + "'"
							+ "and S.created_by ='" + createdBy + "'");
			Session session = sessionFactory.openSession();
			Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
					.addScalar("caseType", StringType.INSTANCE).addScalar("firstName", StringType.INSTANCE)
					.addScalar("lastName", StringType.INSTANCE).addScalar("msisdn", StringType.INSTANCE)
					.addScalar("updatedOn", DateType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
			return (List<ReportResponseDto>) sqlQueryObject.list();
		} else {
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append(
					"select sd.CASE_TYPE as caseType, sd.FIRST_NAME as firstName,sd.LAST_NAME as lastName,s.MSISDN as msisdn,S.updated_by as data_executive, s.updated_on as execution_date,sd.final_status_reason bar_reason"
							+ "FROM SUBSCRIBER S "
							+ "INNER JOIN SUBSCRIBER_DETAILS SD on S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID "
							+ "where trunc(S.created_on) between '" + startDate + "' and '" + endDate + "'"
							+ " and IS_OLD_USER_DETAILS=0  and sd.final_status='" + status + "'" + "and S.created_by ='"
							+ createdBy + "'");
			Session session = sessionFactory.openSession();
			Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
					.addScalar("caseType", StringType.INSTANCE).addScalar("firstName", StringType.INSTANCE)
					.addScalar("lastName", StringType.INSTANCE).addScalar("msisdn", StringType.INSTANCE)
					.addScalar("updatedOn", DateType.INSTANCE).addScalar("reason", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
			return (List<ReportResponseDto>) sqlQueryObject.list();
		}
	}

	/*
	 * @Override public ReportResponseDto sndDailyReport(ReportRequestDto
	 * reportRequestDto) { String
	 * startDate=DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(
	 * ), "dd-MM-yy", "dd-MMM-yy"); String
	 * endDate=DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(),
	 * "dd-MM-yy", "dd-MMM-yy"); String
	 * createdBy=reportRequestDto.getLoggedInUserName(); StringBuilder sqlQuery
	 * = new StringBuilder(); sqlQuery.append(
	 * "select count(s.subscriber_id) totalCount, " +
	 * "count(s.subscriber_id) totalProcessed, " +
	 * "count(case when sd.final_status='APPROVED' and IS_OLD_USER_DETAILS=0 then 1 end) approvedCount, "
	 * +
	 * "count(case when sd.final_status='REJECTED' and IS_OLD_USER_DETAILS=0 then 1  end) rejectedCount, "
	 * +
	 * "count(case when sd.HOTLINE_FLAG=1 and IS_OLD_USER_DETAILS=0 then 1  end) hotLineCount "
	 * + "from users u1 " + "inner join user_roles ur on u1.user_id=ur.user_id "
	 * + "inner join role r on ur.role_id=r.role_id  " +
	 * "inner join users u2 on u1.user_name = u2.created_by " +
	 * "inner join users u3 on u2.user_name = u3.created_by " +
	 * "inner join users u4 on u3.user_name = u4.created_by " +
	 * "inner join users u5 on u4.user_name = u5.created_by " +
	 * "inner join subscriber s on s.created_by = u5.user_name " +
	 * "inner join SUBSCRIBER_DETAILS sd on s.SUBSCRIBER_ID=sd.SUBSCRIBER_ID " +
	 * "where trunc(s.created_on) between '" + startDate + "' and '" + endDate +
	 * "'" + "and u1.user_name ='" + createdBy + "'" ); Session
	 * session=sessionFactory.openSession();
	 * 
	 * Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
	 * .addScalar("totalCount", IntegerType.INSTANCE)
	 * .addScalar("totalProcessed", IntegerType.INSTANCE)
	 * .addScalar("approvedCount", IntegerType.INSTANCE)
	 * .addScalar("rejectedCount", IntegerType.INSTANCE)
	 * .addScalar("hotLineCount", IntegerType.INSTANCE)
	 * .setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
	 * return (ReportResponseDto) sqlQueryObject.uniqueResult(); }
	 */
	@Override
	public ReportResponseDto sndDailyReport(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		Integer roleId = reportRequestDto.getRoleId();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select rbm.user_name ,count(distinct s.subscriber_id) totalCount, "
				+ "count(distinct s.subscriber_id) totalProcessed, "
				+ "count(distinct case when sd.final_status='APPROVED' and IS_OLD_USER_DETAILS=0 then 1 end) approvedCount, "
				+ "count(distinct case when sd.final_status='PENDING' and IS_OLD_USER_DETAILS=0 then 1  end) pendingCount, "
				+ "count(distinct case when sd.final_status='REJECTED' and IS_OLD_USER_DETAILS=0 then 1 end) rejectedCount, "
				+ "count(distinct case when sd.final_status='BARRED' and IS_OLD_USER_DETAILS=0 then 1  end) barredCount, "
				+ "count(distinct case when sd.HOTLINE_FLAG=1 and IS_OLD_USER_DETAILS=0 then 1  end) hotLineCount "
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id "
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ " and trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ "group by rbm.user_name" + "UNION ALL " + "select" + "tbm.user_name as user_name,"
				+ "count(distinct s.subscriber_id) total_registrations,"
				+ "count(distinct s.subscriber_id) total_processed,"
				+ "count(distinct case when sd.final_status='APPROVED' and IS_OLD_USER_DETAILS=0 then s.subscriber_id end) APPROVED_COUNT,"
				+ "count(distinct case when sd.final_status='PENDING' and IS_OLD_USER_DETAILS=0 then s.subscriber_id  end) PENDING_COUNT,"
				+ "count(distinct case when sd.final_status='REJECTED' and IS_OLD_USER_DETAILS=0 then s.subscriber_id  end) REJECTED_COUNT,"
				+ "count(distinct case when sd.final_status='BARRED' and IS_OLD_USER_DETAILS=0 then s.subscriber_id  end) BARRED_COUNT,"
				+ "count(distinct case when sd.HOTLINE_FLAG=1 and IS_OLD_USER_DETAILS=0 then s.subscriber_id  end) HOTLINE_COUNT"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID= ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ " and trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ " and NVL(rbm.user_name,rbm.auuid)='" + createdBy + "'" + "group by  tbm.user_name " + "UNION ALL "

				+ " select cp.user_name," + "count(distinct s.subscriber_id) total_registrations,"
				+ "count(distinct s.subscriber_id) total_processed,"
				+ "count(distinct case when sd.final_status='APPROVED' and IS_OLD_USER_DETAILS=0 then 1 end) APPROVED_COUNT,"
				+ "count(distinct case when sd.final_status='PENDING' and IS_OLD_USER_DETAILS=0 then 1  end) PENDING_COUNT,"
				+ "count(distinct case when sd.final_status='REJECTED' and IS_OLD_USER_DETAILS=0 then 1  end) REJECTED_COUNT,"
				+ "count(distinct case when sd.final_status='BARRED' and IS_OLD_USER_DETAILS=0 then 1  end) BARRED_COUNT,"
				+ "count(distinct case when sd.HOTLINE_FLAG=1 and IS_OLD_USER_DETAILS=0 then 1  end) HOTLINE_COUNT"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "--inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ " and NVL(tbm.user_name,tbm.auuid)='" + createdBy + "'" + "group by  cp.user_name "

		);
		Session session = sessionFactory.openSession();

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("totalCount", IntegerType.INSTANCE)
				.addScalar("totalProcessed", IntegerType.INSTANCE).addScalar("approvedCount", IntegerType.INSTANCE)
				.addScalar("rejectedCount", IntegerType.INSTANCE).addScalar("hotLineCount", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (ReportResponseDto) sqlQueryObject.uniqueResult();
	}

	// dont need this method
	@Override
	public ReportResponseDto tbmDailyReport(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select count(s.subscriber_id) totalCount, " + "count(s.subscriber_id) totalProcessed, "
				+ "count(case when sd.final_status='APPROVED' and IS_OLD_USER_DETAILS=0 then 1 end) approvedCount, "
				+ "count(case when sd.final_status='REJECTED' and IS_OLD_USER_DETAILS=0 then 1  end) rejectedCount, "
				+ "count(case when sd.HOTLINE_FLAG=1 and IS_OLD_USER_DETAILS=0 then 1  end) hotLineCount "
				+ "from users u1 " + "inner join user_roles ur on u1.user_id=ur.user_id "
				+ "inner join role r on ur.role_id=r.role_id  " + "inner join users u2 on u1.user_name = u2.created_by "
				+ "inner join users u3 on u2.user_name = u3.created_by "
				+ "inner join subscriber s on s.created_by = u3.user_name "
				+ "inner join SUBSCRIBER_DETAILS sd on s.SUBSCRIBER_ID=sd.SUBSCRIBER_ID "
				+ "where trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'" + "and u1.user_name ='"
				+ createdBy + "'");
		Session session = sessionFactory.openSession();
		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("totalCount", IntegerType.INSTANCE)
				.addScalar("totalProcessed", IntegerType.INSTANCE).addScalar("approvedCount", IntegerType.INSTANCE)
				.addScalar("rejectedCount", IntegerType.INSTANCE).addScalar("hotLineCount", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (ReportResponseDto) sqlQueryObject.uniqueResult();
	}

	@Override
	public List<ReportResponseDto> sndUserPerformance(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		Integer roleId = reportRequestDto.getRoleId();
		String status = reportRequestDto.getStatus();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("    select " + "rbm.user_name as user_name,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from users tbm"
				+ "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"

				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ "group by   rbm.user_name"

				+ "UNION ALL " + "select " + "tbm.user_name as user_name,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from users tbm"
				+ "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ " and NVL(rbm.user_name,rbm.auuid)='" + createdBy + "'" + "group by  tbm.user_name "

				+ "UNION ALL " + " select" + "cp.user_name as cp_username,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from users tbm"
				+ "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "--inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID= ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on s.created_by = tbm.user_name or s.created_by=tbm.auuid"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "--and tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name like 'DM%'))"
				+ "and tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+

				"and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'" + " and tbm.user_name='"
				+ createdBy + "' or tbm.auuid='" + createdBy + "'" + "group by  cp.user_name ");

		Session session = sessionFactory.openSession();

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("cpName", StringType.INSTANCE)
				.addScalar("mtd", IntegerType.INSTANCE).addScalar("lmtd", IntegerType.INSTANCE)
				.addScalar("growth", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (List<ReportResponseDto>) sqlQueryObject.list();
	}

	private class TransferRowMapper implements RowMapper<ReportResponseDto> {

		@Override
		public ReportResponseDto mapRow(ResultSet rs, int index) throws SQLException {
			ReportResponseDto reportResponseDto = new ReportResponseDto();
			reportResponseDto.setTotalRegistration(rs.getInt("totalRegistration"));
			return reportResponseDto;
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List<ReportResponseDto> sndUserQuality(ReportRequestDto reportRequestDto) {
		/*
		 * List<ReportResponseDto> records = null; try { String
		 * startDate=DateUtils.getDateConvertedFormat(reportRequestDto.
		 * getStartDate(), "dd-MM-yy", "dd-MMM-yy"); String
		 * endDate=DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(
		 * ), "dd-MM-yy", "dd-MMM-yy"); String
		 * createdBy=reportRequestDto.getLoggedInUserName(); Integer
		 * roleId=reportRequestDto.getRoleId(); SimpleJdbcCall jdbcCall = new
		 * SimpleJdbcCall(dataSource);
		 * //jdbcCall.withCatalogName("PACKAGE_NAME");
		 * jdbcCall.withProcedureName("sndUser_Quality");
		 * 
		 * jdbcCall.declareParameters( new SqlParameter("startDate",
		 * Types.VARCHAR), new SqlParameter("endDate", Types.VARCHAR), new
		 * SqlParameter("createdBy", Types.VARCHAR), new SqlParameter("roleId",
		 * Types.NUMERIC), new SqlOutParameter("Cur", OracleTypes.CURSOR,new
		 * TransferRowMapper())//, //new SqlOutParameter("p_msg", Types.VARCHAR)
		 * ); // set param in value Map<String, Object> inData = new
		 * HashMap<String, Object>(); inData.put("startDate", startDate);
		 * inData.put("endDate", endDate); inData.put("createdBy", createdBy);
		 * inData.put("roleId", roleId); // get param out value Map<String,
		 * Object> outData = jdbcCall.execute(inData); records =
		 * (List<ReportResponseDto>) outData.get("Cur"); //String msgSuccess =
		 * (String) outData.get("p_msg"); } catch (Exception e) {
		 * e.printStackTrace(); } return records;
		 */
		/*
		 * List<ReportResponseDto> list =null; String
		 * startDate=DateUtils.getDateConvertedFormat(reportRequestDto.
		 * getStartDate(), "dd-MM-yy", "dd-MMM-yy"); String
		 * endDate=DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(
		 * ), "dd-MM-yy", "dd-MMM-yy"); String
		 * createdBy=reportRequestDto.getLoggedInUserName(); Integer
		 * roleId=reportRequestDto.getRoleId(); String
		 * status=reportRequestDto.getStatus(); try { String sql=
		 * "{call sndUser_Quality(?,?,?,?)}"; list =jdbcTemplate.query(sql, new
		 * Object[] {startDate,endDate,createdBy,roleId},new
		 * BeanPropertyRowMapper(ReportResponseDto.class));
		 * 
		 * } catch (NoResultException exp) { return null; } catch (Exception
		 * exp) { exp.printStackTrace(); } return list;
		 */
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		Integer roleId = reportRequestDto.getRoleId();
		String status = reportRequestDto.getStatus();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("   select rbm.user_name as user_name," + "count(s.subscriber_id) customer_registrations,"
				+ "count(case when sd.final_status='APPROVED' and sd.IS_OLD_USER_DETAILS=0 then 1 end)*100/count(s.subscriber_id) APPROVED_percent,"
				+ "count(case when sd.final_status='REJECTED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) REJECTED_percent,"
				+ "count(case when sd.final_status='BARRED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) BARRED_percent,"
				+ "count(case when sd.HOTLINE_FLAG=1 and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) HOTLINE_percent"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"

				+ "group by  rbm.user_name " + "UNION ALL " + "  select tbm.user_name as user_name,"
				+ "count(s.subscriber_id) customer_registrations,"
				+ "count(case when sd.final_status='APPROVED' and sd.IS_OLD_USER_DETAILS=0 then 1 end)*100/count(s.subscriber_id) APPROVED_percent,"
				+ "count(case when sd.final_status='REJECTED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) REJECTED_percent,"
				+ "count(case when sd.final_status='BARRED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) BARRED_percent,"
				+ "count(case when sd.HOTLINE_FLAG=1 and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) HOTLINE_percent"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ " and NVL(rbm.user_name,rbm.auuid)='" + createdBy + "' " + "group by  tbm.user_name " + "UNION ALL "
				+ "select cp.user_name as cp_username," + "count(s.subscriber_id) customer_registrations,"
				+ "count(case when sd.final_status='APPROVED' and sd.IS_OLD_USER_DETAILS=0 then 1 end)*100/count(s.subscriber_id) APPROVED_percent,"
				+ "count(case when sd.final_status='REJECTED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) REJECTED_percent,"
				+ "count(case when sd.final_status='BARRED' and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) BARRED_percent,"
				+ "count(case when sd.HOTLINE_FLAG=1 and sd.IS_OLD_USER_DETAILS=0 then 1  end)*100/count(s.subscriber_id) HOTLINE_percent"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "--inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'"
				+ " and NVL(tbm.user_name,tbm.auuid)='" + createdBy + "' " + "group by  cp.user_name "

		);
		Session session = sessionFactory.openSession();
		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("userName", StringType.INSTANCE)
				.addScalar("totalRegistration", IntegerType.INSTANCE)
				.addScalar("percentageApproved", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (List<ReportResponseDto>) sqlQueryObject.list();
	}

	@Override
	public List<ReportResponseDto> sndChannelActivity(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		Integer roleId = reportRequestDto.getRoleId();
		// String status=reportRequestDto.getStatus();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("   select" + "r.role_name as channel,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_active,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_active,"
				+ "count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_inactive,"
				+ "(count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100/count(s.subscriber_id) as lmtd_inactive_percent"
				+ "from users cp" + "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = cp.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where  cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22')) "
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'" + "group by r.role_name "
				+ "UNION ALL " + "select" + "r.role_name as channel,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_active,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_active,"
				+ "count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_inactive,"
				+ "(count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100/count(s.subscriber_id) as lmtd_inactive_percent"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = cp.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ " and NVL(rbm.user_name,rbm.auuid)='" + createdBy + "' " + "and  trunc(s.created_on) between '"
				+ startDate + "' and '" + endDate + "'" + "group by r.role_name " + "UNION ALL " + "select"
				+ "r.role_name as channel,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_active,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_active,"
				+ "count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_inactive,"
				+ "(count(s.subscriber_id) - count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100/count(s.subscriber_id) as lmtd_inactive_percent"
				+ "from users tbm" + "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID= ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on s.created_by = tbm.user_name or s.created_by=tbm.auuid"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = tbm.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and tbm.user_name='" + createdBy + "'  or tbm.auuid='" + createdBy + "' "
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'" + "group by r.role_name "

		);
		Session session = sessionFactory.openSession();
		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("channel", StringType.INSTANCE)
				.addScalar("atm", IntegerType.INSTANCE).addScalar("alm", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (List<ReportResponseDto>) sqlQueryObject.list();
	}

	@Override
	public List<ReportResponseDto> sndChannelPerformance(ReportRequestDto reportRequestDto) {
		String startDate = DateUtils.getDateConvertedFormat(reportRequestDto.getStartDate(), "dd-MM-yy", "dd-MMM-yy");
		String endDate = DateUtils.getDateConvertedFormat(reportRequestDto.getEndDate(), "dd-MM-yy", "dd-MMM-yy");
		String createdBy = reportRequestDto.getLoggedInUserName();
		Integer roleId = reportRequestDto.getRoleId();
		// String status=reportRequestDto.getStatus();
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("select " + "r.role_name as cp_name,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from" + "users cp"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = cp.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where  cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "and  trunc(s.created_on) between '" + startDate + "' and '" + endDate + "'" + "group by r.role_name "
				+ "UNION ALL " + "select " + "r.role_name as cp_name,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from users tbm"
				+ "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = cp.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and rbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'ZBM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "NVL(rbm.user_name,rbm.auuid)= '" + createdBy + "'" + "and  trunc(s.created_on) between '" + startDate
				+ "' and '" + endDate + "'" + "group by r.role_name " + "UNION ALL " + "select " + "r.role_name,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end) as mtd_total,"
				+ "count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end) as lmtd_total,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)) end) as growth,"
				+ "(case when count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end)=0 then 0 else"
				+ "(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-1))+1) and sysdate then 1 else null end)"
				+ "- count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))*100"
				+ "/(count(case when trunc(s.created_on) between (last_day(add_months(sysdate,-2))+1) and last_day(add_months(sysdate,-1)) then 1 else null end))"
				+ "end) as growth_percent" + "from users tbm"
				+ "inner join USER_DISTRICT ut_tbm on ut_tbm.user_id = tbm.user_id"
				+ "inner join USER_PROVINCE ur_rbm on ur_rbm.PROVINCE_ID = ut_tbm.USER_PROVINCE_ID"
				+ "--inner join users rbm on rbm.user_id = ur_rbm.user_id"
				+ "inner join USER_DISTRICT ut_cp on ut_cp.DISTRICT_ID = ut_tbm.DISTRICT_ID"
				+ "inner join users cp on cp.user_id = ut_cp.user_id"
				+ "inner join subscriber s on NVL(cp.user_name,cp.auuid)=s.created_by"
				+ "inner join subscriber_details sd on s.subscriber_id=sd.subscriber_id"
				+ "inner join user_roles ur_cp on ur_cp.USER_ID = cp.USER_ID"
				+ "inner join role r on r.ROLE_ID = ur_cp.ROLE_ID"
				+ "where tbm.user_id in (select user_id from user_roles where role_id in (select role_id from role where role_name = 'TSM'))"
				+ "and cp.user_id in (select user_id from user_roles where role_id in (select role_id from role where parent_role_id = '22'))"
				+ "NVL(tbm.user_name,tbm.auuid)= '" + createdBy + "'" + "and  trunc(s.created_on) between '" + startDate
				+ "' and '" + endDate + "'" + "group by r.role_name "

		);
		Session session = sessionFactory.openSession();
		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString()).addScalar("channelName", StringType.INSTANCE)
				.addScalar("mtd", IntegerType.INSTANCE).addScalar("lmtd", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(ReportResponseDto.class));
		return (List<ReportResponseDto>) sqlQueryObject.list();
	}
}