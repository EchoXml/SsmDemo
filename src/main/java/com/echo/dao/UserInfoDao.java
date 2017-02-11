package com.echo.dao;

import com.echo.entity.UserInfo;

public interface UserInfoDao {
	
	/**
	 * 根据用户名查询是否存在该用户
	 * @param username 要查询的用户名
	 * @return 
	 */
	Integer queryByUserName(String username);
	
	/**
	 * 根据传入对象中的用户名密码匹配是否存在对应的用户信息
	 * @param userInfo
	 * @return
	 */
	UserInfo queryByNameAndPwd(UserInfo userInfo);
	
	/**
	 * 往数据库中添加一条用户记录
	 * @param userInfo
	 * @return
	 */
	Integer addUserInfo(UserInfo userInfo);

}
