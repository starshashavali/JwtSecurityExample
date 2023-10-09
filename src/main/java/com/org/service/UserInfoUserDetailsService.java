package com.org.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.org.config.UserInfoUserDetails;
import com.org.domain.UserInfo;
import com.org.dto.UserDtlsRequest;
import com.org.repo.UserInfoRepo;

@Component
public class UserInfoUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepo repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserInfo> userInfo = repository.findByName(username);
        return userInfo.map(UserInfoUserDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("user not found " + username));

    }
    
    public String saveUser(UserDtlsRequest dto) {
    	UserInfo entity=new UserInfo();
    	BeanUtils.copyProperties(dto, entity);
    	PasswordEncoder encoder=new BCryptPasswordEncoder();
		String pwd = encoder.encode(entity.getPassword());
		entity.setPassword(pwd);

    	repository.save(entity);
    	return "success";
    }
}
