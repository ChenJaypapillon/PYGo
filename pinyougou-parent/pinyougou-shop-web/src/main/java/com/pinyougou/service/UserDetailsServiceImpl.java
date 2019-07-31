package com.pinyougou.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.service.SellerService;

public class UserDetailsServiceImpl implements UserDetailsService {
	private SellerService SellerService;
	

	public void setSellerService(SellerService sellerService) {
		SellerService = sellerService;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		System.out.println("经过了service方法");
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		
		//得到商家对象
		
		TbSeller seller = SellerService.findOne(username);
		
		if (seller!=null) {
			if (seller.getStatus().equals("1")) {
				System.out.println(username);
				System.out.println("111");
				return new User(username, seller.getPassword(), authorities);

			}else {
				
				return null;
			}
			
		}else {
			return null;
		}
		
		
		
		
	}

}
