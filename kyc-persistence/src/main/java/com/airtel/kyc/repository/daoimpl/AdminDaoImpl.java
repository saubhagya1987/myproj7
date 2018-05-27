package com.airtel.kyc.repository.daoimpl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.airtel.kyc.repository.dao.AdminDao;
import com.airtel.kyc.repository.entity.Admin;

@Repository
public class AdminDaoImpl extends GenericDaoImpl<Admin, Integer> 
                        implements AdminDao {
    @Override
    public boolean removeAdmin(Integer id) {
        Query employeeTaskQuery = currentSession().createQuery(
                "from Admin u where :id");
        employeeTaskQuery.setParameter("id", id);
        return employeeTaskQuery.executeUpdate() > 0;
    }
 
    @Override
    public boolean isAdminRegistered(String userName, String password) {
        /*You can use any character instead of 'A'. If a record is found, 
        only single character, in this example 'A', will return from database
        */
        Query employeeTaskQuery = currentSession().createQuery(
                "select 'A' from Admin u where username=:username and password=:password");
        employeeTaskQuery.setParameter("username", userName);
        employeeTaskQuery.setParameter("password", password);
        return employeeTaskQuery.list().size() > 0;
    }
 
    @Override
    public Admin getAdmin(String username) {
        Query query = currentSession().createQuery(
                "from Admin " +
                        "where username=:username");
        query.setParameter("username", username);
        return (Admin) query.uniqueResult();
 
    }
}