package com.wish.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wish.dao.support.IBaseDao;
import com.wish.domain.entity.Role;

@Repository
public interface IRoleDao extends IBaseDao<Role, Integer> {

	Page<Role> findAllByNameContainingOrDescriptionContaining(String searchText1,String searchText2, Pageable pageable);

}
