package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;

public interface ShoppingcarDAO {
	// ���ﳵ��������
	boolean insertShopping(User user, Greens greens);

	// �鿴���ﳵ�Ƿ�������
	Shoppingcar checkShopping(User user, Greens greens);
	
	// ���¹��ﳵ
	boolean updateShopping(User user, Greens greens);
	
	// ɾ������
	boolean deleteShopping(User user,Greens greens);
	
	// ����userid�鿴���ﳵ
	List<Shoppingcar> findShoppingList(int currentPage, int pageSize, int userid);
	
	int findTotalCount(int userid);
}
