package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;

public interface SuperadminService {

	// ����ƽ̨����
	boolean updateSuperamount(Greens greens);

	// ��¼
	Superadmin superLogin(String supernumber, String superpassword);
}
