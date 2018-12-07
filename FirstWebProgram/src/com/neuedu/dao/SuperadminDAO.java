package com.neuedu.dao;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;

public interface SuperadminDAO {

	// 更新平台收益
	boolean updateSuperamount(Greens greens);

	// 登录
	Superadmin superLogin(String supernumber, String superpassword);
}
