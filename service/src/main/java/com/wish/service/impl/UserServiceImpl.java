package com.wish.service.impl;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.wish.common.utils.MD5Utils;
import com.wish.dao.IUserDao;
import com.wish.dao.mapper.UserMapper;
import com.wish.dao.support.IBaseDao;
import com.wish.domain.po.RolePO;
import com.wish.domain.po.UserPO;
import com.wish.service.IRoleService;
import com.wish.service.IUserService;
import com.wish.service.support.impl.BaseServiceImpl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * <p>
 * 用户账户表  服务实现类
 * </p>
 *
 * @author SPPan
 * @since 2016-12-28
 */
@Service
public class UserServiceImpl extends BaseServiceImpl<UserPO, Integer> implements IUserService {

	/** 新用户默认密码 **/
	private final static String DEFAULT_PASSWORD = "123456";

	@Autowired
	private IUserDao userDao;

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private IRoleService roleService;
	
	@Override
	public IBaseDao<UserPO, Integer> getBaseDao() {
		return this.userDao;
	}

	@Override
	public UserPO findByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public void saveOrUpdate(UserPO user) {
		if(user.getId() != null){
			UserPO dbUser = find(user.getId());
			dbUser.setNickName(user.getNickName());
			dbUser.setSex(user.getSex());
			dbUser.setBirthday(user.getBirthday());
			dbUser.setTelephone(user.getTelephone());
			dbUser.setEmail(user.getEmail());
			dbUser.setAddress(user.getAddress());
			dbUser.setLocked(user.getLocked());
			dbUser.setDescription(user.getDescription());
			dbUser.setUpdateTime(new Date());
			update(dbUser);
		}else{
			user.setCreateTime(new Date());
			user.setUpdateTime(new Date());
			user.setDeleteStatus(0);
			user.setPassword(MD5Utils.md5(DEFAULT_PASSWORD));
			save(user);
		}
	}
	
	

	@Override
	public void delete(Integer id) {
		UserPO user = find(id);
		Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能删除");
		super.delete(id);
	}

	@Override
	public void grant(Integer id, String[] roleIds) {
		UserPO user = userMapper.findUserById(id);
		Assert.notNull(user, "用户不存在");
		Assert.state(!"admin".equals(user.getUserName()),"超级管理员用户不能修改管理角色");
		RolePO role;
		Set<RolePO> roles = new HashSet<RolePO>();
		if(roleIds != null){
			for (int i = 0; i < roleIds.length; i++) {
				Integer rid = Integer.parseInt(roleIds[i]);
				role = roleService.find(rid);
				roles.add(role);
			}
		}
		user.setRoles(roles);
		update(user);
	}

	@Override
	public Page<UserPO> findAllByLike(String searchText, PageRequest pageRequest) {
		if(StringUtils.isBlank(searchText)){
			searchText = "";
		}
		return userDao.findAllByNickNameContaining(searchText,pageRequest);
	}

	
	@Override
	public void updatePwd(UserPO user, String oldPassword, String password1, String password2) {
		Assert.notNull(user, "用户不能为空");
		Assert.notNull(oldPassword, "原始密码不能为空");
		Assert.notNull(password1, "新密码不能为空");
		Assert.notNull(password2, "重复密码不能为空");
		
		UserPO dbUser = userDao.findByUserName(user.getUserName());
		Assert.notNull(dbUser, "用户不存在");
		
		Assert.isTrue(user.getPassword().equals(MD5Utils.md5(oldPassword)), "原始密码不正确");;
		Assert.isTrue(password1.equals(password2), "两次密码不一致");
		dbUser.setPassword(MD5Utils.md5(password1));
		userDao.saveAndFlush(dbUser);
	}
	
}
