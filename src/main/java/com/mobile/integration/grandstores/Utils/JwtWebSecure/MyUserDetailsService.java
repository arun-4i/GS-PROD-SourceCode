package com.mobile.integration.grandstores.Utils.JwtWebSecure;

import java.util.ArrayList;

// import com.mobile.integration.grandstores.POJO.UserPoJo;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
        // UserPoJo user=new UserPoJo();
        return new User(userid, userid, new ArrayList<>());
        // return new User("foo", "foo", new ArrayList<>());
        // if("1114".equalsIgnoreCase(userid)){
        //     return new User(userid, "4iApps", new ArrayList<>());
        // }else{
        //     throw new UsernameNotFoundException("User not found with username: " + userid);
        // }

    }
}
