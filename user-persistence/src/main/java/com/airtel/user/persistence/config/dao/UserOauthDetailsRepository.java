package com.airtel.user.persistence.config.dao;

import com.airtel.user.persistence.entities.Users;
import com.airtel.user.persistence.exception.UserDaoException;

public interface UserOauthDetailsRepository {
	public Users loadUserByName(String userName) throws UserDaoException;
}
