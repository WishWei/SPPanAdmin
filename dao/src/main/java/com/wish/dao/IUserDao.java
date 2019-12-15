package com.wish.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import com.wish.dao.support.IBaseDao;
import com.wish.domain.entity.User;

@Repository
public interface IUserDao extends IBaseDao<User, Integer> {

	User findByUserName(String username);

	Page<User> findAllByNickNameContaining(String searchText, Pageable pageable);

}
