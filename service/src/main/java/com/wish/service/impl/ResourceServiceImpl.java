package com.wish.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.wish.common.Constats;
import com.wish.dao.IResourceDao;
import com.wish.domain.po.ResourcePO;
import com.wish.domain.po.RolePO;
import com.wish.domain.vo.ZtreeVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wish.dao.support.IBaseDao;
import com.wish.service.IResourceService;
import com.wish.service.IRoleService;
import com.wish.service.support.impl.BaseServiceImpl;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class ResourceServiceImpl extends BaseServiceImpl<ResourcePO, Integer>
		implements IResourceService {

	@Autowired
	private IResourceDao resourceDao;

	@Autowired
	private IRoleService roleService;

	@Override
	public IBaseDao<ResourcePO, Integer> getBaseDao() {
		return this.resourceDao;
	}

	@Override
	@Cacheable(value= Constats.RESOURCECACHENAME,key="'tree_' + #roleId")
	public List<ZtreeVO> tree(int roleId) {
		List<ZtreeVO> resulTreeNodes = new ArrayList<ZtreeVO>();
		RolePO role = roleService.find(roleId);
		Set<ResourcePO> roleResources = role.getResources();
		resulTreeNodes.add(new ZtreeVO(0L, null, "系统菜单", true));
		ZtreeVO node;
		List<ResourcePO> all = resourceDao.findAllByOrderByParentAscIdAscSortAsc();
		for (ResourcePO resource : all) {
			node = new ZtreeVO();
			node.setId(Long.valueOf(resource.getId()));
			if (resource.getParent() == null) {
				node.setpId(0L);
			} else {
				node.setpId(Long.valueOf(resource.getParent().getId()));
			}
			node.setName(resource.getName());
			if (roleResources != null && roleResources.contains(resource)) {
				node.setChecked(true);
			}
			resulTreeNodes.add(node);
		}
		return resulTreeNodes;
	}

	@Override
	public void saveOrUpdate(ResourcePO resource) {
		if(resource.getId() != null){
			ResourcePO dbResource = find(resource.getId());
			dbResource.setUpdateTime(new Date());
			dbResource.setName(resource.getName());
			dbResource.setSourceKey(resource.getSourceKey());
			dbResource.setType(resource.getType());
			dbResource.setSourceUrl(resource.getSourceUrl());
			dbResource.setLevel(resource.getLevel());
			dbResource.setSort(resource.getSort());
			dbResource.setIsHide(resource.getIsHide());
			dbResource.setIcon(resource.getIcon());
			dbResource.setDescription(resource.getDescription());
			dbResource.setUpdateTime(new Date());
			dbResource.setParent(resource.getParent());
			update(dbResource);
		}else{
			resource.setCreateTime(new Date());
			resource.setUpdateTime(new Date());
			save(resource);
		}
	}

	@Override
	public void delete(Integer id) {
		resourceDao.deleteGrant(id);
		super.delete(id);
	}

	@Override
	public Page<ResourcePO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return resourceDao.findAllByNameContaining(searchText, pageRequest);
	}
	
}
