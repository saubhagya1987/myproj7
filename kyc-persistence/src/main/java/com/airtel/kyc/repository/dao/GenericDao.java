package com.airtel.kyc.repository.dao;

import java.util.List;
import java.util.Map;

import com.airtel.kyc.repository.entity.BaseEntity;
import com.airtel.kyc.repository.exception.KycDaoException;

/**
 * @author Saubhagya
 *
 * @param <E>
 * @param <K>
 */
public interface GenericDao<E,K> {
    /**
     * @param entity
     */
    public void add(E entity) ;
    /**
     * @param entity
     */
    public void saveOrUpdate(E entity) ;
    /**
     * @param entity
     */
    public void update(E entity) ;
    /**
     * @param entity
     */
    public void remove(E entity);
    /**
     * @param key
     * @return
     */
    public E find(K key);
    /**
     * @return
     */
    public List<E> getAll() ;
    /**
     * @param queryName
     * @param fieldValue
     * @return
     */
    E findSingleResultByNamedQuery(String queryName, Map<String, Object> fieldValue);
    /**
     * @param queryName
     * @param fieldValue
     * @return
     */
    List<E> findResultsByNamedQuery(String queryName, Map<String, Object> fieldValue);
    /**
     * @param queryName
     * @param fieldValue
     * @return
     */
    E findSingleResultByHqlQuery(String queryName, Map<String, Object> fieldValue);
    /**
     * @param queryName
     * @param fieldValue
     * @return
     */
    List<E> findResultsByHQLQuery(String queryName, Map<String, Object> fieldValue);
	/**
	 * @param queryName
	 * @param fieldValue
	 * @return
	 */	
	List<E> findResultsByHQLINQuery(String queryName, List<Integer> regionIdList);
	
	
	E findSingleResultBySQLQuery(String queryName);
	
	List<E> findResultsBySQLQuery(String queryName,Object entity);
	
	E findResultBySQLQuery(String queryName,Object entity);
	
	E saveOrUpdateEntity(Object object);
}