package com.wish.service;

import com.wish.domain.po.RolePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wish.service.support.IBaseService;

/**
 * <p>
 * 角色服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IRoleService extends IBaseService<RolePO,Integer> {

	/**
	 * 添加或者修改角色
	 * @param role
	 */
	void saveOrUpdate(RolePO role);

	/**
	 * 给角色分配资源
	 * @param id 角色ID
	 * @param resourceIds 资源ids
	 */
	void grant(Integer id, String[] resourceIds);

	/**
	 * 根据关键字查询分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<RolePO> findAllByLike(String searchText, PageRequest pageRequest);
	
}
