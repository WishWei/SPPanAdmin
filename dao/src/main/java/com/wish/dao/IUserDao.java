package com.wish.dao;

import com.wish.domain.po.UserPO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wish.dao.support.IBaseDao;

@Repository
public interface IUserDao extends IBaseDao<UserPO, Integer> {

	UserPO findByUserName(String username);

	Page<UserPO> findAllByNickNameContaining(String searchText, Pageable pageable);

}
