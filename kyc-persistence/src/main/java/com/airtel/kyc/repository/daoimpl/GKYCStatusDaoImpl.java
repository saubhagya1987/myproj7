package com.airtel.kyc.repository.daoimpl;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.airtel.kyc.helpers.dto.GKYCStatusRequestDto;
import com.airtel.kyc.helpers.dto.GKYCStatusResponseDto;
import com.airtel.kyc.repository.dao.GKYCStatusDao;

@Repository
public class GKYCStatusDaoImpl implements GKYCStatusDao{

	private static Logger logger = Logger.getLogger(GKYCStatusDaoImpl.class);
	private static boolean isDebugEnabled = logger.isDebugEnabled();
	
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
    JdbcTemplate jdbcTemplate;
	
	@Autowired
    DataSource dataSource;
	
	@Override
	public GKYCStatusResponseDto getGKYCStatus(GKYCStatusRequestDto pGKYCStatusRequestDto) {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT distinct ALL_DATA.*, UD.MSISDN as retailerMSISDN, (FIRST_NAME||' '||LAST_NAME) as retailerName "
				+ "FROM(SELECT SUB_DET.*, U.USER_ID FROM(SELECT SUB.*, ID_NO as identificationNo, ID_TYPE as identificationType FROM( "
		+ "SELECT SUBSCRIBER_DETAILS_ID,SD.MSISDN as MSISDN, SD.CREATED_BY, SD.CREATED_ON as dateOfRegistration, SD.DATE_OF_BIRTH as dateOfBirth, FIRST_NAME as firstName, LAST_NAME as lastName, SD.GENDER as gender, SD.SIM_SERIAL_NUMBER as simNo, "
		+ "SD.IP_ADDRESS as source,SD.UPDATED_ON as lastUpdteDate,SD.FINAL_STATUS as kysStatus "
		+ "FROM SUBSCRIBER S, SUBSCRIBER_DETAILS SD WHERE S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID AND (S.UPDATED_ON=SD.UPDATED_ON or SD.IS_OLD_USER_DETAILS=0) "
		+ "AND S.MSISDN='"+pGKYCStatusRequestDto.gettMSISDN()+"' "
		+ ")SUB, SUBSCRIBER_ID_DETAILS SUB_ID_DET WHERE SUB.SUBSCRIBER_DETAILS_ID=SUB_ID_DET.SUBSCRIBER_DETAILS_ID "
		+ ")SUB_DET, USERS U WHERE (NVL(U.USER_NAME,U.AUUID) = SUB_DET.CREATED_BY OR NVL(U.AUUID,U.USER_NAME) = SUB_DET.CREATED_BY) "
		+ ")ALL_DATA, USER_DETAILS UD WHERE ALL_DATA.USER_ID=UD.USER_ID AND UD.IS_OLD_USER_DETAILS=0");
		
		Session session=sessionFactory.openSession();
		

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
				.addScalar("MSISDN", StringType.INSTANCE)
				.addScalar("simNo", StringType.INSTANCE)
				.addScalar("firstName", StringType.INSTANCE)
				.addScalar("lastName", StringType.INSTANCE)
				.addScalar("dateOfBirth", StringType.INSTANCE)
				.addScalar("identificationNo", StringType.INSTANCE)
				.addScalar("identificationType", StringType.INSTANCE)
				.addScalar("gender", StringType.INSTANCE)
				.addScalar("dateOfRegistration", StringType.INSTANCE)
				.addScalar("retailerName", StringType.INSTANCE)
				.addScalar("retailerMSISDN", StringType.INSTANCE)
				.addScalar("source", StringType.INSTANCE)
				.addScalar("lastUpdteDate", StringType.INSTANCE)
				.addScalar("kysStatus", StringType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(GKYCStatusResponseDto.class));
		return  (GKYCStatusResponseDto) sqlQueryObject.uniqueResult();
	}

	@Override
	public GKYCStatusResponseDto getSimValidationFailedSubscriber(GKYCStatusRequestDto pGKYCStatusRequestDto) {
		StringBuilder sqlQuery = new StringBuilder();
		sqlQuery.append("SELECT ALL_DATA.*, UD.MSISDN as retailerMSISDN, (FIRST_NAME||' '||LAST_NAME) as retailerName "
				+ "FROM(SELECT SUB_DET.*, U.USER_ID FROM(SELECT SUB.*, ID_NO as identificationNo, ID_TYPE as identificationType FROM( "
		+ "SELECT SUBSCRIBER_DETAILS_ID,SD.MSISDN as MSISDN, SD.CREATED_BY, SD.CREATED_ON as dateOfRegistration, SD.DATE_OF_BIRTH as dateOfBirth, FIRST_NAME as firstName, LAST_NAME as lastName, SD.GENDER as gender, SD.SIM_SERIAL_NUMBER as simNo, "
		+ "SD.IP_ADDRESS as source,SD.UPDATED_ON as lastUpdteDate,SD.FINAL_STATUS as kysStatus ,S.SUBSCRIBER_ID as subscriberId "
		+ "FROM SUBSCRIBER S, SUBSCRIBER_DETAILS SD WHERE S.SUBSCRIBER_ID=SD.SUBSCRIBER_ID AND (S.UPDATED_ON=SD.UPDATED_ON or SD.IS_OLD_USER_DETAILS=0) "
		+ "AND S.MSISDN='"+pGKYCStatusRequestDto.gettMSISDN()+"' "
		+ " AND NVL(SD.sim_validation_flag,'0')='0' "
		+ ")SUB, SUBSCRIBER_ID_DETAILS SUB_ID_DET WHERE SUB.SUBSCRIBER_DETAILS_ID=SUB_ID_DET.SUBSCRIBER_DETAILS_ID "
		+ ")SUB_DET, USERS U WHERE (NVL(U.USER_NAME,U.AUUID) = SUB_DET.CREATED_BY OR NVL(U.AUUID,U.USER_NAME) = SUB_DET.CREATED_BY) "
		+ ")ALL_DATA, USER_DETAILS UD WHERE ALL_DATA.USER_ID=UD.USER_ID AND UD.IS_OLD_USER_DETAILS=0 ");
		
		Session session=sessionFactory.openSession();
		

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
				.addScalar("MSISDN", StringType.INSTANCE)
				.addScalar("simNo", StringType.INSTANCE)
				.addScalar("firstName", StringType.INSTANCE)
				.addScalar("lastName", StringType.INSTANCE)
				.addScalar("dateOfBirth", StringType.INSTANCE)
				.addScalar("identificationNo", StringType.INSTANCE)
				.addScalar("identificationType", StringType.INSTANCE)
				.addScalar("gender", StringType.INSTANCE)
				.addScalar("dateOfRegistration", StringType.INSTANCE)
				.addScalar("retailerName", StringType.INSTANCE)
				.addScalar("retailerMSISDN", StringType.INSTANCE)
				.addScalar("source", StringType.INSTANCE)
				.addScalar("lastUpdteDate", StringType.INSTANCE)
				.addScalar("kysStatus", StringType.INSTANCE)
				.addScalar("subscriberId", IntegerType.INSTANCE)
				.setResultTransformer(Transformers.aliasToBean(GKYCStatusResponseDto.class));
		return  (GKYCStatusResponseDto) sqlQueryObject.uniqueResult();
	}
	
	
	@Override
	public GKYCStatusResponseDto getAuuId(Integer userId) {
		StringBuilder sqlQuery = new StringBuilder();
		//sqlQuery.append("SELECT NVL(AUUID,USER_NAME)auuid FROM USERS WHERE USER_ID='"+userId+"' ");
		sqlQuery.append("SELECT NVL(U.AUUID,U.USER_NAME)auuid ,(FIRST_NAME||' '||LAST_NAME)userName FROM USERS U, USER_DETAILS UD WHERE U.USER_ID=UD.USER_ID  AND UD.IS_OLD_USER_DETAILS=0 AND U.USER_ID='"+userId+"' ");
		
		Session session=sessionFactory.openSession();
		

		Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
				.addScalar("auuid", StringType.INSTANCE)	
				.addScalar("userName", StringType.INSTANCE)	
				.setResultTransformer(Transformers.aliasToBean(GKYCStatusResponseDto.class));
		return  (GKYCStatusResponseDto) sqlQueryObject.uniqueResult();
	}
}