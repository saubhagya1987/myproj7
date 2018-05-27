package com.airtel.kyc.repository.daoimpl;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StringType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.airtel.kyc.helpers.dto.SubscriberDto;
import com.airtel.kyc.repository.dao.SubscriberDao;
import com.airtel.kyc.repository.entity.Subscriber;

@Repository
public class SubscriberDaoImpl extends GenericDaoImpl<Subscriber, Integer> implements SubscriberDao{
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Subscriber getSubscriberData(String msisdn, String simSerialNo, Integer isOldData) {
		HashMap<String, Object> map = new HashMap<>();
		//String namedQuery = "Subscriber.findByMsisdnSimSerialNumberIssOldSubscriber";
		String namedQuery ="Select s from Subscriber s where msisdn=:msisdn and simSerialNumber=:simSerialNumber";
		map.put("msisdn", msisdn);
		map.put("simSerialNumber", simSerialNo);
		
		/*if (isOldData != 0)
		{			
			namedQuery += " and isOldSubscriber=:isOldSubscriber";
			map.put("isOldSubscriber", isOldData);
		}*/		
		return findSingleResultByHqlQuery(namedQuery, map);
	}

	@Override
	public Subscriber getSubscriberObj(String msisdn) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);				
		return findSingleResultByNamedQuery("Subscriber.findByMsisdn", map);
	}

	@Override
	public Subscriber getSubscriberBySubscriberId(Long subscriberId) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("subscriberId", subscriberId);				
		return findSingleResultByNamedQuery("Subscriber.findBySubscriberId", map);
	}

	@Override
	public Subscriber getApprovedSubscriberData(String msisdn, Integer isOldSubscriber,String finalStatus) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);	
		//map.put("isOldSubscriber", isOldSubscriber);
		map.put("finalStatus", finalStatus);
		return findSingleResultByNamedQuery("Subscriber.findByMsisdnIsOldSubscriberFInalStatus", map);

	}

	@Override
	public Subscriber getSubscriberObject(String msisdn, Integer isOldSubscriber) {
		HashMap<String, Object> map = new HashMap<>();		
		map.put("msisdn", msisdn);	
		//map.put("isOldSubscriber", isOldSubscriber);		
		return findSingleResultByNamedQuery("Subscriber.findByMsisdnIsOldSubscriber", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SubscriberDto> getApprovedSubscriber() {		
			
			StringBuilder sqlQuery = new StringBuilder();
			sqlQuery.append("select sd.msisdn,sd.first_name,sd.last_name,sd.date_of_birth,d.district_name,"
					+ "c.country_name,sd.FINAL_STATUS as gsm_reg_status,'PASS' as am_reg_status,null as territory,null as region,"
					+ "null as site_id,sid.id_type,sid.id_number,upper(sd.GENDER)gender,ad.PERMANENT_ADDRESS as physical_address,"
					+ "DECODE(sd.IS_MINOR,0,'FALSE',1,'TRUE') as is_minor,sd.latitude,sd.longitude,"
					+ "sd.channel_partner_cell_id,u.auuid as agent_auuid,u.USER_NAME as agent_msisdn,"
					+ " ud.AIRTEL_HANDSET_IMEI as agent_imei,ud.FIRST_NAME as agent_fname,ud.LAST_NAME as agent_lname,r.role_name as cp_type"
					+", sd.CREATED_ON as timestamp"
                    + "  from subscriber s "
                    +"inner join subscriber_details sd on s.subscriber_id = sd.subscriber_id "
                    +"inner join address ad on ad.address_id = sd.address_id "
                    +"inner join district d on ad.district_id = d.district_id "
                    +"inner join country c on c.COUNTRY_ID = ad.country_id "  
                    +"inner join subscriber_id_details sid on sd.subscriber_details_id = sid.subscriber_details_id "
                    +"inner join users u on (sd.created_by = u.auuid or sd.created_by = u.USER_NAME) "
                    +"inner join user_details ud on u.user_id = ud.user_id "
                    +"inner join user_roles ur on ur.user_id = u.user_id "
                    +"inner join role r on r.role_id = ur.role_id "
                    +"where sd.FINAL_STATUS = 'APPROVED' "
                    +"and sd.AM_ACTIVATION_TIME is not null "
                    +"and ud.IS_OLD_USER_DETAILS = 0 "
					+" and s.created_on > sysdate - INTERVAL '1' hour");
                   // +"and TO_DATE(to_char(s.created_on,'DD-MM-YY'))=to_date(to_char(sysdate,'DD-MON-YY'))-1");
			
            Session session = sessionFactory.openSession();
			Query sqlQueryObject = session.createSQLQuery(sqlQuery.toString())
					.addScalar("msisdn", StringType.INSTANCE)
					.addScalar("first_name", StringType.INSTANCE)
					.addScalar("last_name", StringType.INSTANCE)
					.addScalar("date_of_birth", StringType.INSTANCE)
					.addScalar("district_name", StringType.INSTANCE)
					.addScalar("country_name", StringType.INSTANCE)
					.addScalar("gsm_reg_status", StringType.INSTANCE)
					.addScalar("am_reg_status", StringType.INSTANCE)
					.addScalar("territory", StringType.INSTANCE)
					.addScalar("region", StringType.INSTANCE)
					.addScalar("site_id", StringType.INSTANCE)
					.addScalar("id_type", StringType.INSTANCE)
					.addScalar("id_number", StringType.INSTANCE)
					.addScalar("gender", StringType.INSTANCE)
					.addScalar("physical_address", StringType.INSTANCE)
					.addScalar("is_minor", StringType.INSTANCE)
					.addScalar("latitude", StringType.INSTANCE)
					.addScalar("longitude", StringType.INSTANCE)
					.addScalar("channel_partner_cell_id", StringType.INSTANCE)
					
					.addScalar("agent_auuid", StringType.INSTANCE)
					.addScalar("agent_msisdn", StringType.INSTANCE)
					.addScalar("agent_imei", StringType.INSTANCE)
					.addScalar("agent_fname", StringType.INSTANCE)
					.addScalar("agent_lname", StringType.INSTANCE)
					.addScalar("cp_type", StringType.INSTANCE)
					.addScalar("timestamp", StringType.INSTANCE)
					.setResultTransformer(Transformers.aliasToBean(SubscriberDto.class));
			return (List<SubscriberDto>) sqlQueryObject.list();
	}
	
	
}
