package com.neuedu.dao;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;

public interface SuperadminDAO {

	// ����ƽ̨����
	boolean updateSuperamount(Greens greens);

	// ��¼
	Superadmin superLogin(String supernumber, String superpassword);
}
