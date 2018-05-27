package com.airtel.user.services;

import com.airtel.user.helper.dto.ClientOauthDetailsDto;
import com.airtel.user.persistence.exception.UserDaoException;


public interface ClientOauthDetailsService{
	public ClientOauthDetailsDto getByClientId(String clientId) throws UserDaoException;
	public ClientOauthDetailsDto getByDeviceId(String deviceId) throws UserDaoException;
}
