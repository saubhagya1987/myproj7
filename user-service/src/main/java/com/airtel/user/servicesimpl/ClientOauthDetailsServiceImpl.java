package com.airtel.user.servicesimpl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.airtel.user.helper.dto.ClientOauthDetailsDto;
import com.airtel.user.persistence.config.dao.ClientOauthDetailsRepository;
import com.airtel.user.persistence.entities.ClientOauthDetails;
import com.airtel.user.persistence.exception.UserDaoException;
import com.airtel.user.services.ClientOauthDetailsService;


@Service("clientOauthDetailsService")
public class ClientOauthDetailsServiceImpl implements ClientOauthDetailsService {

	@Autowired
	@Qualifier("clientOauthDetailsRepository")
	private ClientOauthDetailsRepository clientOauthDetailsRepository;
	
	@Override
	public ClientOauthDetailsDto getByClientId(String clientId) throws UserDaoException {
		// TODO Auto-generated method stub
		
		ClientOauthDetails clientOauthDetails=clientOauthDetailsRepository.getByClientId(clientId);
		ClientOauthDetailsDto ClientOauthDetailsDto=new ClientOauthDetailsDto();
		
		BeanUtils.copyProperties(clientOauthDetails,ClientOauthDetailsDto);
		/*Users user =clientOauthDetails.getUser();
		
		UsersDto usersDto= new UsersDto();
		BeanUtils.copyProperties(user,usersDto);
		
		List<Role> roleList=user.getRoles();
		
		List<RoleDto> roleDtoList=new ArrayList<>();
		
		for (Role role : roleList) {
			RoleDto roleDto=new RoleDto();
			BeanUtils.copyProperties(role,roleDto);
			roleDtoList.add(roleDto);
		}
		
		usersDto.setRoles(roleDtoList);
		ClientOauthDetailsDto.setUser(usersDto);*/		
		return ClientOauthDetailsDto;
	}

	@Override
	public ClientOauthDetailsDto getByDeviceId(String deviceId) throws UserDaoException {
		// TODO Auto-generated method stub
		ClientOauthDetails clientOauthDetails=clientOauthDetailsRepository.getByDeviceId(deviceId);
		ClientOauthDetailsDto ClientOauthDetailsDto=new ClientOauthDetailsDto();
		
		BeanUtils.copyProperties(clientOauthDetails,ClientOauthDetailsDto);
	/*	Users user =clientOauthDetails.getUser();
		
		UsersDto usersDto= new UsersDto();
		BeanUtils.copyProperties(user,usersDto);
		
		List<Role> roleList=user.getRoles();
		
		List<RoleDto> roleDtoList=new ArrayList<>();
		
		for (Role role : roleList) {
			RoleDto roleDto=new RoleDto();
			BeanUtils.copyProperties(role,roleDto);
			roleDtoList.add(roleDto);
		}
		
		usersDto.setRoles(roleDtoList);
		ClientOauthDetailsDto.setUser(usersDto);		*/
		return ClientOauthDetailsDto;
	}

	
}
