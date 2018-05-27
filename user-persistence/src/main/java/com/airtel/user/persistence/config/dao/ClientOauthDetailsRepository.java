package com.airtel.user.persistence.config.dao;

import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.exception.UserDaoException;


public interface ClientOauthDetailsRepository{
	public ClientOauthDetails getByClientId(String clientId) throws UserDaoException;
	public ClientOauthDetails getByDeviceId(String deviceId) throws UserDaoException;
}
