package com.wish.dao;

import com.wish.domain.po.RolePO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wish.dao.support.IBaseDao;

@Repository
public interface IRoleDao extends IBaseDao<RolePO, Integer> {

	Page<RolePO> findAllByNameContainingOrDescriptionContaining(String searchText1, String searchText2, Pageable pageable);

}
