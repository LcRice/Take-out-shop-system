package com.neuedu.service.Impl;

import com.neuedu.dao.SuperadminDAO;
import com.neuedu.dao.Impl.SuperadminDAOImpl;
import com.neuedu.entity.Greens;
import com.neuedu.entity.Superadmin;
import com.neuedu.service.SuperadminService;

public class SuperadminServiceImpl implements SuperadminService {

	private SuperadminDAO superadminDAO = new SuperadminDAOImpl();
	public boolean updateSuperamount(Greens greens) {
		// TODO Auto-generated method stub
		return superadminDAO.updateSuperamount(greens);
	}
	public Superadmin superLogin(String supernumber, String superpassword) {
		// TODO Auto-generated method stub
		return superadminDAO.superLogin(supernumber, superpassword);
	}

}
