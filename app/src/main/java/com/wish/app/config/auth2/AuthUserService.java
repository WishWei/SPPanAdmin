package com.wish.app.config.auth2;


import com.wish.domain.po.UserPO;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by wangl on 2017/2/5.
 */

public interface AuthUserService extends UserDetailsService {
    UserPO getUserById(int id);
}
