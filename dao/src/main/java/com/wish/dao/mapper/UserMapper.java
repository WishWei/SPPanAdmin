package com.wish.dao.mapper;

import com.wish.domain.po.UserPO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: SPPanAdmin
 * @description:
 * @author: wish
 * @create: 2019-12-05 21:15
 **/
@Mapper
public interface UserMapper {
    /**
     * 根据id查询用户
     * @param id 用户id
     * @return
     */
    UserPO findUserById(Integer id);
}
