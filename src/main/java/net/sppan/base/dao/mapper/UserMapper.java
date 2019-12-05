package net.sppan.base.dao.mapper;

import net.sppan.base.entity.User;

/**
 * @program: SPPanAdmin
 * @description:
 * @author: wish
 * @create: 2019-12-05 21:15
 **/
public interface UserMapper {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return
     */
    User findUserById(Integer id);
}
