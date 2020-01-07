package com.wish.app.config.auth2;

import com.wish.app.config.security.CustomUserDetails;
import com.wish.dao.IUserDao;
import com.wish.domain.po.UserPO;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;

/**
 * Created by wangl on 2017/2/10.
 */
@Service
public class AuthAuthUserServiceImpl implements AuthUserService {
    @Resource
    private IUserDao userDao;

    @Override
    public UserPO getUserById(int id) {
        Optional<UserPO> optional = userDao.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserPO userPO = userDao.findByUserName(username);
        if (userPO == null) {
            throw new UsernameNotFoundException("Could not find the user '" + username + "'");
        }
        CustomUserDetails userDetails = new CustomUserDetails();
        BeanUtils.copyProperties(userPO, userDetails);
        userDetails.setRoles(userPO.getRoles());
        return userDetails;
    }
}
