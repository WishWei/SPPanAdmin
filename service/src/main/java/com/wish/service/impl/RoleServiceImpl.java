package com.wish.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wish.common.Constats;
import com.wish.dao.IRoleDao;
import com.wish.domain.po.ResourcePO;
import com.wish.domain.po.RolePO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.wish.dao.support.IBaseDao;
import com.wish.service.IResourceService;
import com.wish.service.IRoleService;
import com.wish.service.support.impl.BaseServiceImpl;

/**
 * <p>
 * 角色表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class RoleServiceImpl extends BaseServiceImpl<RolePO, Integer> implements IRoleService {

	@Autowired
	private IRoleDao roleDao;
	@Autowired
	private IResourceService resourceService;
	
	@Override
	public IBaseDao<RolePO, Integer> getBaseDao() {
		return this.roleDao;
	}

	@Override
	public void saveOrUpdate(RolePO role) {
		if(role.getId() != null){
			RolePO dbRole = find(role.getId());
			dbRole.setUpdateTime(new Date());
			dbRole.setName(role.getName());
			dbRole.setDescription(role.getDescription());
			dbRole.setUpdateTime(new Date());
			dbRole.setStatus(role.getStatus());
			update(dbRole);
		}else{
			role.setCreateTime(new Date());
			role.setUpdateTime(new Date());
			save(role);
		}
	}

	
	
	@Override
	public void delete(Integer id) {
		RolePO role = find(id);
		Assert.state(!"administrator".equals(role.getRoleKey()),"超级管理员角色不能删除");
		super.delete(id);
	}

	@Override
	@CacheEvict(value = Constats.RESOURCECACHENAME, key = "'tree_' + #id")
	public void grant(Integer id, String[] resourceIds) {
		RolePO role = find(id);
		Assert.notNull(role, "角色不存在");
		
		Assert.state(!"administrator".equals(role.getRoleKey()),"超级管理员角色不能进行资源分配");
		ResourcePO resource;
		Set<ResourcePO> resources = new HashSet<ResourcePO>();
		if(resourceIds != null){
			for (int i = 0; i < resourceIds.length; i++) {
				if(StringUtils.isBlank(resourceIds[i]) || "0".equals(resourceIds[i])){
					continue;
				}
				Integer rid = Integer.parseInt(resourceIds[i]);
				resource = resourceService.find(rid);
				resources.add(resource);
			}
		}
		role.setResources(resources);
		update(role);
	}

	@Override
	public Page<RolePO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return roleDao.findAllByNameContainingOrDescriptionContaining(searchText,searchText, pageRequest);
	}
	
}
