package com.echo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echo.dao.UserInfoDao;
import com.echo.dto.Result;
import com.echo.entity.UserInfo;
import com.echo.enums.RegisterStateEnum;
import com.echo.service.UserInfoService;

@Service
public class UserInfoServiceImpl  implements UserInfoService{

	@Autowired
	private UserInfoDao userInfoDao;
	
	@Override
	public String checkUserNameExsit(String username) {
		Integer check=userInfoDao.queryByUserName(username);
		if (check==1) {//数据库已存在该用户名
			return "{\"isExsit\":true,\"msg\":\"该用户名已存在\"}";
		}
		return "{\"isExsit\":false,\"msg\":\"该用户名可以被使用\"}";
	}

	@Override
	public UserInfo login(UserInfo userInfo) {
		
		return userInfoDao.queryByNameAndPwd(userInfo);
	}

	@Override
	public Result<RegisterStateEnum> register(UserInfo userInfo) {
		Integer insert=userInfoDao.addUserInfo(userInfo);
		Result<RegisterStateEnum> result=null;
		if (insert==1) {
			result=new Result<RegisterStateEnum>(true);
			result.setData(RegisterStateEnum.SUCCESS);
		}else{
			result=new Result<RegisterStateEnum>(false);
			result.setData(RegisterStateEnum.ERROR);
		}
		return result;
	}

}
