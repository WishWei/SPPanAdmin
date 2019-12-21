package com.wish.service;

import java.util.List;

import com.wish.domain.po.ResourcePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.wish.service.support.IBaseService;
import com.wish.domain.vo.ZtreeVO;

/**
 * <p>
 * 资源服务类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
public interface IResourceService extends IBaseService<ResourcePO, Integer> {

	/**
	 * 获取角色的权限树
	 * @param roleId
	 * @return
	 */
	List<ZtreeVO> tree(int roleId);

	/**
	 * 修改或者新增资源
	 * @param resource
	 */
	void saveOrUpdate(ResourcePO resource);

	/**
	 * 关键字分页
	 * @param searchText
	 * @param pageRequest
	 * @return
	 */
	Page<ResourcePO> findAllByLike(String searchText, PageRequest pageRequest);

}
