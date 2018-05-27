package com.airtel.kyc.business.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.airtel.kyc.business.exception.BusinessException;
import com.airtel.kyc.business.exception.UserException;
import com.airtel.kyc.business.service.HelperDataManagementService;
import com.airtel.kyc.constants.KycConstants;
import com.airtel.kyc.enumData.ExceptionCodes;
import com.airtel.kyc.helpers.dto.BaseResponse;
import com.airtel.kyc.helpers.dto.BranchDto;
import com.airtel.kyc.helpers.dto.ClusterDto;
import com.airtel.kyc.helpers.dto.CommissionRuleDto;
import com.airtel.kyc.helpers.dto.CountryDto;
import com.airtel.kyc.helpers.dto.DistrictDto;
import com.airtel.kyc.helpers.dto.HelpAndSupportDto;
import com.airtel.kyc.helpers.dto.ProvinceDto;
import com.airtel.kyc.helpers.dto.SiteDto;
import com.airtel.kyc.helpers.dto.VillageDto;
import com.airtel.kyc.helpers.dto.ZoneDto;
import com.airtel.kyc.repository.dao.BranchDao;
import com.airtel.kyc.repository.dao.ClusterDao;
import com.airtel.kyc.repository.dao.CommissionDao;
import com.airtel.kyc.repository.dao.ConfigDataDAO;
import com.airtel.kyc.repository.dao.CountryDao;
import com.airtel.kyc.repository.dao.DistrictDao;
import com.airtel.kyc.repository.dao.HelpAndSupportDao;
import com.airtel.kyc.repository.dao.KycDaoService;
import com.airtel.kyc.repository.dao.ProvinceDao;
import com.airtel.kyc.repository.dao.SiteDao;
import com.airtel.kyc.repository.dao.VillageDao;
import com.airtel.kyc.repository.dao.ZoneDao;
import com.airtel.kyc.repository.entity.Branch;
import com.airtel.kyc.repository.entity.Cluster_;
import com.airtel.kyc.repository.entity.CommissionRule;
import com.airtel.kyc.repository.entity.ConfigData;
import com.airtel.kyc.repository.entity.Country;
import com.airtel.kyc.repository.entity.District;
import com.airtel.kyc.repository.entity.HelpAndSupport;
import com.airtel.kyc.repository.entity.Location;
import com.airtel.kyc.repository.entity.Province;
import com.airtel.kyc.repository.entity.Site;
import com.airtel.kyc.repository.entity.Village;
import com.airtel.kyc.repository.entity.Zone;
import com.airtel.kyc.repository.exception.KycDaoException;
import com.airtel.user.exception.UserBusinessException;
import com.airtel.user.helper.dto.DepartmentDto;
import com.airtel.user.helper.dto.LocationDto;
import com.airtel.user.helper.dto.RoleDto;
import com.airtel.user.helper.dto.UsersDto;
import com.airtel.user.persistence.config.dao.UserDaoService;
import com.airtel.user.persistence.entities.Department;
import com.airtel.user.persistence.entities.Role;
import com.airtel.user.persistence.exception.UserDaoException;
import com.airtel.user.services.UserService;

@Service
public class HelperDataManagementServiceImpl implements HelperDataManagementService {

	@SuppressWarnings("rawtypes")
	@Autowired
	private ZoneDao zoneDao;
	
	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private ProvinceDao territoryDao;
	
	@Autowired
	private ClusterDao clusterDao;
	
	@Autowired
	private SiteDao siteDao;
	
	/*@Autowired
	private RegionDao regionDao;*/
	
	@Autowired
	private ProvinceDao provinceDao;
	
	@Autowired
	private DistrictDao districtDao;
	
	@Autowired
	private CountryDao countryDao;
	
	@Autowired
	private CommissionDao commissionDao;
	
	@Autowired
	private VillageDao parishDao;
	
	@Autowired
	private ConfigDataDAO configDataDAO;
	
    @Autowired
    private HelpAndSupportDao helpAndSupportDao;
	
	/*@Autowired
	RoleDao roleDao;*/
	
	@Qualifier("userDaoSession")
	@Autowired
	private UserDaoService userDaoService;
	
	@Qualifier("uerService")
	@Autowired
	private UserService userService;
	
	@SuppressWarnings("rawtypes")
	@Qualifier("kycDaoSession")
	@Autowired
	private KycDaoService kycDaoService;

	@Override
	public Map<String, Object> getSitesMapping() throws BusinessException {
		Map<String, Object> map = null;
		
			List<ZoneDto> dtos = new ArrayList<>();
			@SuppressWarnings("unchecked")
			List<Zone> list = zoneDao.findZoneList();
			if (list != null) {
				for (Zone zone : list) {
					ZoneDto tmp = getZoneToZoneDto(zone);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("zones", dtos);
			}

		
		return map;
	}

	@Override
	public Map<String, Object> getZones() throws BusinessException {
		Map<String, Object> map = null;
		
			List<ZoneDto> dtos = new ArrayList<>();			
			@SuppressWarnings("unchecked")
			List<Zone> list = zoneDao.findZoneList();
			if (list != null) {
				for (Zone zone : list) {
					ZoneDto tmp = new ZoneDto();
					zone.setBranchs(null);
					BeanUtils.copyProperties(zone, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("zones", dtos);
			}

		
		return map;
	}

	@Override
	public Map<String, Object> getBranchByZoneId(int zoneId) throws BusinessException {
		Map<String, Object> map = null;
		if (zoneId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<BranchDto> dtos = new ArrayList<>();			
			@SuppressWarnings("unchecked")
			List<Branch> list = branchDao.findBranchList(zoneId);
			if (list != null) {
				for (Branch branch : list) {
					BranchDto tmp = new BranchDto();
					branch.setClusters(null);
					BeanUtils.copyProperties(branch, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("branches", dtos);
			}

		
		return map;
	}

	@Override
	public Map<String, Object> getClusterByBranchId(int branchId) throws BusinessException {
		Map<String, Object> map = null;
		if (branchId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<ClusterDto> dtos = new ArrayList<>();			
			@SuppressWarnings("unchecked")
			List<Cluster_> list = clusterDao.findClusterList(branchId);
			if (list != null) {
				for (Cluster_ cluster_ : list) {
					ClusterDto tmp = new ClusterDto();
					cluster_.setSites(null);
					BeanUtils.copyProperties(cluster_, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("clusters", dtos);
			}
		
		return map;
	}

	@Override
	public Map<String, Object> getSitesByClusterId(int clusterId) throws BusinessException {
		Map<String, Object> map = null;
		if (clusterId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}		
			List<SiteDto> dtos = new ArrayList<>();			
			@SuppressWarnings("unchecked")
			List<Site> list = siteDao.findSites(clusterId);
			if (list != null) {
				for (Site site : list) {
					SiteDto tmp = new SiteDto();
					BeanUtils.copyProperties(site, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("sites", dtos);
			}
		
		return map;
	}

	@SuppressWarnings("unchecked")
	private ZoneDto getZoneToZoneDto(Zone zone) throws BusinessException {
		if (zone == null) {
			return null;
		}
		ZoneDto tmp = new ZoneDto();

		// Set branches
		Map<String, Object> mapBranch = getBranchByZoneId(zone.getZoneId());
		if (mapBranch != null && mapBranch.get("branches") != null)
			tmp.setBranches((List<BranchDto>) mapBranch.get("branches"));
		else
			tmp.setBranches(null);
		BeanUtils.copyProperties(zone, tmp);

		if (tmp.getBranches() != null) {
			List<BranchDto> branches = new ArrayList<>();
			for (BranchDto branchDto : tmp.getBranches()) {
				Map<String, Object> mapCluster = getClusterByBranchId(branchDto.getBranchId());
				if (mapCluster != null && mapCluster.get("clusters") != null)
					branchDto.setClusters((List<ClusterDto>) mapCluster.get("clusters"));
				else
					branchDto.setClusters(null);

				if (branchDto.getClusters() != null) {
					List<ClusterDto> clusterDtos = new ArrayList<>();
					for (ClusterDto clusterDto : branchDto.getClusters()) {
						Map<String, Object> mapSites = getSitesByClusterId(clusterDto.getClusterId());
						if (mapSites != null && mapSites.get("sites") != null)
							clusterDto.setSites((List<SiteDto>) mapSites.get("sites"));
						else
							clusterDto.setSites(null);
						clusterDtos.add(clusterDto);
					}
					branchDto.setClusters(clusterDtos);
				}
				branches.add(branchDto);
			}
			// new Branches set
			tmp.setBranches(branches);
		}

		return tmp;
	}

	@Override
	//public List<DepartmentDto> getDepartments(int slot,int startIndex) throws BusinessException {
	public List<DepartmentDto> getDepartments() throws BusinessException {
		List<DepartmentDto> dtos = new ArrayList<>();
		try {
			//List<Department> list = (List<Department>) this.userDaoService.findByCriteria(Department.class, null,slot,startIndex);  
			List<Department> list = (List<Department>) this.userDaoService.findByCriteria(Department.class, null);
			if (list == null || list.size() < 1) {
				throw new BusinessException(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION);
			}
			for (Department department : list) {
				DepartmentDto departmentDto = new DepartmentDto();
				BeanUtils.copyProperties(department, departmentDto);
				dtos.add(departmentDto);
			}
		}  catch (UserDaoException e) {
			e.printStackTrace();
			throw new BusinessException(ExceptionCodes.SYSTEM_EXCEPTION);
		}
		return dtos;
	}

/*	@Override
	public Map<String, Object> getRegionDistrictWithCountry() throws BusinessException {
		Map<String, Object> map  = new HashMap<>();
		
			List<RegionDto> dtos = new ArrayList<>();
			List<DistrictDto> dtosDistrictDto =null;
			List<CountryDto> countrydtos = new ArrayList<>();
			Map<String, Object> map2 = new LinkedHashMap<>();
			@SuppressWarnings("unchecked")
			List<Region> list = regionDao.findRegions();
			if (list != null) {
				for (Region region : list) {
					RegionDto tmp = new RegionDto();
					//region.setDistricts(null);
					BeanUtils.copyProperties(region, tmp);
					map2 = new HashMap<>();
					map2.put("region", region);
					@SuppressWarnings("unchecked")
					List<District> listDistrict = districtDao.findDistrict();
					if(listDistrict != null) {
						dtosDistrictDto = new ArrayList<>();
						for(District district : listDistrict) {
							DistrictDto districtDto = new DistrictDto();
							BeanUtils.copyProperties(district, districtDto);
							dtosDistrictDto.add(districtDto);
						}
						//tmp.setDistricts(dtosDistrictDto);
					}
					dtos.add(tmp);
				}
				map.put("region", dtos);
			}


			@SuppressWarnings("unchecked")
			List<Country> countryList = countryDao.findCountries();
			if (countryList != null) {
				for (Country country: countryList) {
					CountryDto tmp = new CountryDto();
					BeanUtils.copyProperties(country, tmp);
					countrydtos.add(tmp);
				}
				map.put("countries", countrydtos);
			}

		
		return map;
	}*/

	/*@Override
	public Map<String, Object> getRegion() throws BusinessException {
		Map<String, Object> map = null;
		
		List<RegionDto> dtos = new ArrayList<>();		
		List<Region> list =regionDao.findRegions();
		if (list != null) {
			for (Region region : list) {
				RegionDto tmp = new RegionDto();
				region.setTerritory(null);
				BeanUtils.copyProperties(region, tmp);
				dtos.add(tmp);
			}
			map = new HashMap<>();
			map.put("regions", dtos);
		}

	
	return map;
	}*/

	/*@Override
	public Map<String, Object> getTerritoryByRegionId(int regionId) throws BusinessException {
		Map<String, Object> map = null;
		if (regionId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<ProvinceDto> dtos = new ArrayList<>();		
			
			List<Territory> list = territoryDao.findTerritoryList(regionId);
			if (list != null) {
				for (Territory territory : list) {
					ProvinceDto tmp = new ProvinceDto();
					territory.setDistricts(null);
					BeanUtils.copyProperties(territory, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("territory", dtos);
			}

		
		return map;
	}*/

	/*@Override
	public Map<String, Object> getDistrictByTerritoryId(int territoryId) throws BusinessException {
		Map<String, Object> map = null;
		if (territoryId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<DistrictDto> dtos = new ArrayList<>();				
			List<District> list = districtDao.findDistrictList(territoryId);
			if (list != null) {
				for (District district : list) {
					DistrictDto tmp = new DistrictDto();
					tmp.setDistrictId(district.getDistrictId());
					tmp.setDistrictName(district.getDistrictName());
					//BeanUtils.copyProperties(district, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("district", dtos);
			}
		
		return map;
	}*/

	/*@Override
	public Map<String, Object> getAllRegion() throws BusinessException {
		Map<String, Object> map = null;		
		List<RegionDto> regionList = new ArrayList<>();	
		List<ProvinceDto> territoryList =null;
		List<DistrictDto> districtList =null;	
		List<ParishDto> parishList =null;	
		List<Region> list = regionDao.findRegions();
			
		if (list.size()>0) {
			for (Region region : list) {
				RegionDto regionDto = new RegionDto();
				BeanUtils.copyProperties(region, regionDto);
				if (region.getTerritory().size()>0) {
					territoryList= new ArrayList<>();	
					for (Territory territory : region.getTerritory()) {
						ProvinceDto territoryDto = new ProvinceDto();
						BeanUtils.copyProperties(territory, territoryDto);
						if (territory.getDistricts().size()>0) {
							districtList= new ArrayList<>();
							for (District district : territory.getDistricts()) {
								DistrictDto districtDto = new DistrictDto();
								BeanUtils.copyProperties(district, districtDto);
								if(district.getParish().size()>0)
								{
									parishList=new ArrayList<>();
									for (Parish parish : district.getParish()) {
										ParishDto parishDto=new ParishDto();
										BeanUtils.copyProperties(parish, parishDto);
										parishList.add(parishDto);
									}
									districtDto.setParish(parishList);
								}								
								
								districtList.add(districtDto);
							}
							territoryDto.setDistricts(districtList);
						}
						
						territoryList.add(territoryDto);
					}
					regionDto.setTerritory(territoryList);
					
				}
				
				
				regionList.add(regionDto);
				map = new HashMap<>();
				map.put("region", regionList);
			}
		}
		return map;
	}
*/
	//@SuppressWarnings("unchecked")
	/*@Override
	public Map<String, Object> getTerritoryList(UsersDto usersDto) throws BusinessException {	
		Map<String, Object> map = null;
		if (usersDto.getLocation().getRegionIds().size() < 1 || usersDto.getLocation().getRegionIds()==null ) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<ProvinceDto> dtos = new ArrayList<>();		
			
			List<Territory> list = territoryDao.findTerritoryLists(usersDto);
			if (list != null) {
				for (Territory territory : list) {
					ProvinceDto tmp = new ProvinceDto();
					territory.setDistricts(null);
					BeanUtils.copyProperties(territory, tmp);
					dtos.add(tmp);
					
					
				}
				map = new HashMap<>();
				map.put("territory", dtos);
			}
			return map;
	}*/

	@Override
	public Map<String, Object> getDistrictList(UsersDto usersDto) throws BusinessException {
		Map<String, Object> map = null;
		if (usersDto.getLocation().getProvinceIds().size() < 1 && usersDto.getLocation().getProvinceIds()==null) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<DistrictDto> dtos = new ArrayList<>();		
						
			List<District> list = districtDao.findDistrictLists(usersDto);
			if (list != null) {
				for (District district : list) {
					DistrictDto tmp = new DistrictDto();					
					BeanUtils.copyProperties(district, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("district", dtos);
			}
			return map;
	}

	

	/*@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getAllRoleWithSubRole() throws BusinessException, UserDaoException, UserBusinessException {
		Map<String, Object> map = null;
		List<RoleDto> roleDtoList=new ArrayList<>();
		List<Role> list = (List<Role>)userService.findRoleList();
		
		if(list!=null)
		{
			for (Role role : list) {
				Integer roleId=role.getRoleId();
				List<Role> subRoleList = userService.findSubRoleList(roleId);
				RoleDto roleDto=new RoleDto();
				BeanUtils.copyProperties(subRoleList, roleDto); 
				roleDtoList.add(roleDto);
			}
			map = new HashMap<>();
			map.put("role", roleDtoList);
		}
		return map;
	}*/

	@Override
	public RoleDto getRoleByName(String roleName) throws UserException {
		if (roleName == null)
			return null;
		RoleDto tmp = new RoleDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("roleName", roleName);
			
			Role role = (Role) this.userDaoService.getEntityByCriteria(Role.class, map2);
			if (role == null)
				return null;
			BeanUtils.copyProperties(role, tmp);
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RoleDto getRoleBySubRoleName(String roleName) throws UserException {
		if (roleName == null)
			return null;
		RoleDto tmp = new RoleDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("roleName", roleName);
			
			Role role = (Role) this.userDaoService.getEntityByCriteria(Role.class, map2);
			if (role == null)
				return null;
			BeanUtils.copyProperties(role, tmp);
			tmp.setParentRoleId(role.getParentRoleId());
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LocationDto getLocation(Long subId) {
		if(subId == null) {
			return null;
		}
		Map<String, Object> parameterMap = new HashMap<>();
		parameterMap.put("subscriberId", subId);
		LocationDto dto = new LocationDto();
		Location locationNew = new Location();
		List<Location> list = null;
		try {
			list = this.kycDaoService.findByCriteria(Location.class, parameterMap);
			if (list != null && list.size() > 0) {
				locationNew = list.get(0);
				dto.setBranchId(locationNew.getBranchId());
				dto.setZoneId(locationNew.getZoneId());
				dto.setClusterId(locationNew.getClusterId());
				dto.setSiteId(locationNew.getSiteId());
			}
		} catch (KycDaoException e1) {
			e1.printStackTrace();
		}
		return dto;
	}

	@Override
	public Map<String, Object> getAllRoles() throws BusinessException, UserBusinessException, UserDaoException {
		Map<String, Object> map = null;		
			
						
			List<RoleDto> list = userService.getAllRoles();
			if (list != null) {
				map = new HashMap<>();
				map.put("roles", list);
				}
				
			
			return map;
	}

	@Override
	public ZoneDto getZoneByName(String zoneName) throws UserException {
		if (zoneName == null)
			return null;
		ZoneDto tmp = new ZoneDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("zoneName", zoneName);
			@SuppressWarnings("unchecked")
			Zone zone = (Zone) this.kycDaoService.getEntityByCriteria(Zone.class, map2);
			if (zone == null)
				return null;
			BeanUtils.copyProperties(zone, tmp);
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}


	@Override
	public DepartmentDto getDepartmentByName(String departmentName) throws UserException {
		if (departmentName == null)
			return null;
		DepartmentDto tmp = new DepartmentDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("departmentName", departmentName);
			@SuppressWarnings("unchecked")
			Department department = (Department) this.userDaoService.getEntityByCriteria(Department.class, map2);
			if (department == null)
				return null;
			BeanUtils.copyProperties(department, tmp);
		} catch (UserDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public BranchDto getBranchByName(String branchName) throws UserException {
		if (branchName == null)
			return null;
		BranchDto tmp = new BranchDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("branchName", branchName);
			@SuppressWarnings("unchecked")
			Branch branch = (Branch) this.kycDaoService.getEntityByCriteria(Branch.class, map2);
			if (branch == null)
				return null;
			BeanUtils.copyProperties(branch, tmp);
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public ClusterDto getClusterByName(String clusterName) throws UserException {
		if (clusterName == null)
			return null;
		ClusterDto tmp = new ClusterDto();
		try {
			Map<String, Object> map2 = new LinkedHashMap<>();
			map2.put("clusterName", clusterName);
			@SuppressWarnings("unchecked")
			Cluster_ cluster_ = (Cluster_) this.kycDaoService.getEntityByCriteria(Cluster_.class, map2);
			if (cluster_ == null)
				return null;
			BeanUtils.copyProperties(cluster_, tmp);
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return tmp;
	}

	@Override
	public boolean updateLocation(LocationDto dto, Integer userId, Long subId) throws BusinessException {
		Map<String, Object> parameterMap = new HashMap<>();
		boolean shouldUpdate = false;
		if(userId != null) {
			parameterMap.put("userId", userId);
			shouldUpdate = true;
		}
		Location locationNew = new Location();
		if(shouldUpdate) {
			List<Location> list = null;
			try {
				list = this.kycDaoService.findByCriteria(Location.class, parameterMap);
				if (list != null && list.size() > 0) {
					for (Location location : list) {
						location.setIsOldRecord(KycConstants.TRUE);
						try {
							this.kycDaoService.saveOrUpdateEntity(location);
						} catch (KycDaoException e) {
							e.printStackTrace();
						}
					}
					locationNew = list.get(0);
				}
			} catch (KycDaoException e1) {
				e1.printStackTrace();
			}
		}
		
		if(userId != null)
			locationNew.setUserId(userId);
		if(dto.getBranchId() != null)
			locationNew.setBranchId(dto.getBranchId());
		if(dto.getZoneId() != null)
			locationNew.setZoneId(dto.getZoneId());
		if(dto.getClusterId() != null)
			locationNew.setClusterId(dto.getClusterId());
		if(dto.getSiteId() != null)
			locationNew.setSiteId(dto.getSiteId());
		locationNew.setIsOldRecord(KycConstants.FALSE);
		if (subId != null) 
			locationNew.setSubscriberId(subId);
		try {
			this.kycDaoService.saveOrUpdateEntity(locationNew);
			return true;
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public Map<String, Object> getDistrictList() throws BusinessException {
		Map<String, Object> map = null;		
			List<DistrictDto> dtos = new ArrayList<>();				
			List<District> list = districtDao.findDistrict();
			if (list != null) {
				for (District district : list) {
					DistrictDto tmp = new DistrictDto();					
					BeanUtils.copyProperties(district, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("district", dtos);
			}
		
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getDepartmentList() throws BusinessException, UserDaoException {
		Map<String, Object> map = null;
		
		List<DepartmentDto> dtos = new ArrayList<>();		
		List<Department> list = (List<Department>) this.userDaoService.findByCriteria(Department.class, null);
		if (list != null) {
			for (Department department : list) {
				DepartmentDto departmentDto = new DepartmentDto();
				BeanUtils.copyProperties(department, departmentDto);
				dtos.add(departmentDto);
			}
			map = new HashMap<>();
			map.put("departments", dtos);
		}
		return map;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public com.airtel.kyc.repository.entity.Templates getTemplates(String locale, String templateName,
			String chanel) {
		com.airtel.kyc.repository.entity.Templates templates = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("templateName", templateName);
			map.put("locale", locale);
			map.put("chanel", chanel);
			templates = (com.airtel.kyc.repository.entity.Templates) this.kycDaoService
					.getEntityByCriteria(com.airtel.kyc.repository.entity.Templates.class, map);
		} catch (KycDaoException e) {
			e.printStackTrace();
		}
		return templates;
	}

	
	
	@Override
	public Map<String, Object> getAllRoleList() throws BusinessException, UserBusinessException, UserDaoException {
		Map<String, Object> map = null;		
			
						
			List<RoleDto> list = userService.getAllRoleList();
			if (list != null) {
				map = new HashMap<>();
				map.put("roleList", list);
				}
				
			
			return map;
	}

	@Override
	public CommissionRuleDto saveCommissionRule(CommissionRuleDto commissionRuleDto ) throws BusinessException {
		CommissionRule commissionRuleObj=commissionDao.getCommissionObj(commissionRuleDto.getSubRoleId());
		if(commissionRuleObj!=null){
			commissionRuleObj.setSubRoleId(commissionRuleDto.getSubRoleId());
			commissionRuleObj.setCommissionValue(commissionRuleDto.getCommissionValue());
			commissionRuleObj.setCreatedOn(new Timestamp(System.currentTimeMillis()));
		}
		else{
			commissionRuleObj=new CommissionRule();
			commissionRuleObj.setCommissionValue(commissionRuleDto.getCommissionValue());
			commissionRuleObj.setSubRoleId(commissionRuleDto.getSubRoleId());
			commissionRuleObj.setCreatedOn(new Timestamp(System.currentTimeMillis()));
			
		}
		commissionDao.saveOrUpdate(commissionRuleObj);	
		return commissionRuleDto;

		
	}

	@Override
	public CommissionRuleDto getCommissionRule(CommissionRuleDto commissionRuleDto) throws BusinessException {
		CommissionRuleDto commissionRule=new CommissionRuleDto();
		CommissionRule commissionRuleObj=commissionDao.getCommissionObj(commissionRuleDto.getSubRoleId());
		if(commissionRuleObj!=null){
		commissionRule.setCommissionValue(commissionRuleObj.getCommissionValue());
		commissionRule.setSubRoleId(commissionRuleObj.getSubRoleId());
		}
		return commissionRule;
	}

	@Override
	public Map<String, Object> getRoleIdByUserName(UsersDto usersDto) throws BusinessException, UserDaoException, UserBusinessException {
		Map<String, Object> map = null;		
		List<RoleDto> list = userService.getSubRoleList(usersDto);
		if (list != null) {
			map = new HashMap<>();
			map.put("subRole", list);
		}			
		
		return map;
	}

	/*@Override
	public Map<String, Object> getParishByDistrictId(int districtId) throws BusinessException {
		Map<String, Object> map = null;
		if (districtId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<ParishDto> dtos = new ArrayList<>();				
			List<Parish> list = parishDao.findParishList(districtId);
			if (list.size()>0) {
				for (Parish parish : list) {
					ParishDto tmp = new ParishDto();					
					BeanUtils.copyProperties(parish, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("parish", dtos);
			}
		
		return map;
	}*/
	
	public BaseResponse<Object> updateConfigData(List<ConfigData> listConfigData)throws BusinessException{
		try{
			for(ConfigData configData:listConfigData){
				this.kycDaoService.saveOrUpdateEntity(configData);
			}
		} catch (KycDaoException e) {
			
		}
		return null;
	}
	public List<ConfigData> getConfigData()throws BusinessException{
		return configDataDAO.getConfigData();
	}

	@Override
	public HelpAndSupportDto saveAndSendHelpAndSupportData(HelpAndSupportDto helpAndSupport) throws BusinessException{
		HelpAndSupport helpAndSupportObj=helpAndSupportDao.getHelpAndSupportObj(helpAndSupport.getMsisdn());
		if(helpAndSupportObj!=null){	
			helpAndSupportObj.setMsisdn(helpAndSupport.getMsisdn());
			helpAndSupportObj.setHelpAndSupportText(helpAndSupport.getHelpAndSupportText());
			helpAndSupportObj.setUpdatedTimeStamp(new Timestamp(System.currentTimeMillis()));
		}
		else{
			helpAndSupportObj=new HelpAndSupport();	
			helpAndSupportObj.setMsisdn(helpAndSupport.getMsisdn());
			helpAndSupportObj.setHelpAndSupportText(helpAndSupport.getHelpAndSupportText());
			helpAndSupportObj.setUpdatedTimeStamp(new Timestamp(System.currentTimeMillis()));
			
		}
		helpAndSupportDao.saveOrUpdate(helpAndSupportObj);	
		return helpAndSupport;
	}

	@Override
	public List<CountryDto> getCountries() throws BusinessException {
		List<CountryDto> dtos = new ArrayList<>();
		try {			  
			List<Country> list = (List<Country>) this.userDaoService.findByCriteria(Country.class, null);
			if (list == null || list.size() < 1) {
				throw new BusinessException(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION);
			}
			for (Country country : list) {
				CountryDto countryDto = new CountryDto();
				BeanUtils.copyProperties(country, countryDto);
				dtos.add(countryDto);
			}
		}  catch (UserDaoException e) {
			e.printStackTrace();
			throw new BusinessException(ExceptionCodes.SYSTEM_EXCEPTION);
		}
		return dtos;
	}

	@Override
	public Map<String, Object> getProvince() throws BusinessException {
		Map<String, Object> map = null;
		
		List<ProvinceDto> dtos = new ArrayList<>();		
		List<Province> list =provinceDao.findProvince();
		if (list != null) {
			for (Province province : list) {
				ProvinceDto tmp = new ProvinceDto();
				province.setDistricts(null);
				BeanUtils.copyProperties(province, tmp);
				dtos.add(tmp);
			}
			map = new HashMap<>();
			map.put("province", dtos);
		}

	
		return map;
	}

	@Override
	public Map<String, Object> getAllProvince() throws BusinessException {
		Map<String, Object> map = null;		
		//List<RegionDto> regionList = new ArrayList<>();	
		List<ProvinceDto> provinceList =new ArrayList<>();
		List<DistrictDto> districtList =null;	
		List<VillageDto> villageList =null;	
		List<Province> list = provinceDao.findProvince();
			
		if (list.size()>0) {			
					for (Province province : list) {
						ProvinceDto provinceDto = new ProvinceDto();
						BeanUtils.copyProperties(province, provinceDto);
						if (province.getDistricts().size()>0) {
							districtList= new ArrayList<>();
							for (District district : province.getDistricts()) {
								DistrictDto districtDto = new DistrictDto();
								BeanUtils.copyProperties(district, districtDto);
								if(district.getVillage().size()>0)
								{
									villageList=new ArrayList<>();
									for (Village village : district.getVillage()) {
										VillageDto villageDto=new VillageDto();
										BeanUtils.copyProperties(village, villageDto);
										villageList.add(villageDto);
									}
									districtDto.setVillages(villageList);
								}								
								
								districtList.add(districtDto);
							}
							provinceDto.setDistricts(districtList);
						}
						
						provinceList.add(provinceDto);
					}
					
				
				
				//regionList.add(regionDto);
				map = new HashMap<>();
				map.put("province", provinceList);
			}
		
		return map;
	}

	@Override
	public Map<String, Object> getDistrictByProvinceId(int provinceId) throws BusinessException {
		Map<String, Object> map = null;
		if (provinceId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<DistrictDto> dtos = new ArrayList<>();				
			List<District> list = districtDao.findDistrictList(provinceId);
			if (list != null) {
				for (District district : list) {
					DistrictDto tmp = new DistrictDto();
					tmp.setDistrictId(district.getDistrictId());
					tmp.setDistrictName(district.getDistrictName());
					//BeanUtils.copyProperties(district, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("district", dtos);
			}
		
		return map;
	}

	@Override
	public Map<String, Object> getVillageByDistrictId(int districtId) throws BusinessException {
		Map<String, Object> map = null;
		if (districtId < 0) {
			throw new BusinessException(ExceptionCodes.INVALID_REQUEST_OBJECT);
		}
		
			List<VillageDto> dtos = new ArrayList<>();				
			List<Village> list = parishDao.findParishList(districtId);
			if (list.size()>0) {
				for (Village village : list) {
					VillageDto tmp = new VillageDto();					
					BeanUtils.copyProperties(village, tmp);
					dtos.add(tmp);
				}
				map = new HashMap<>();
				map.put("village", dtos);
			}
		
		return map;
	}

	@Override
	public List<CountryDto> getAllCountriesWIthCountryCode() throws BusinessException {
		List<CountryDto> dtos = new ArrayList<>();
		try {			  
			List<Country> list = countryDao.findCountries();
			if (list == null || list.size() < 1) {
				throw new BusinessException(ExceptionCodes.DATA_NOT_FOUND_EXCEPTION);
			}
			for (Country country : list) {
				CountryDto countryDto = new CountryDto();
				BeanUtils.copyProperties(country, countryDto);
				dtos.add(countryDto);
			}
		}  catch (Exception e) {
			e.printStackTrace();
			throw new BusinessException(ExceptionCodes.SYSTEM_EXCEPTION);
		}
		return dtos;
	}
}