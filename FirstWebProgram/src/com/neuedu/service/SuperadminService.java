package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;

public interface SuperadminService {

	// 更新平台收益
	boolean updateSuperamount(Greens greens);

	// 登录
	Superadmin superLogin(String supernumber, String superpassword);
}
