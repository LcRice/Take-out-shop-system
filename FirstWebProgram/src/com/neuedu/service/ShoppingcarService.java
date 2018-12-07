package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.entity.User;
import com.neuedu.vo.ShoppingcarPage;

public interface ShoppingcarService {
	// ���ﳵ��������
	boolean insertShopping(User user, Greens greens);

	// �鿴���ﳵ�Ƿ�������
	boolean checkShopping(User user, Greens greens);

	// ���¹��ﳵ
	boolean updateShopping(User user, Greens greens);

	// ɾ������
	boolean deleteShopping(User user, Greens greens);

	// �û���ҳ��ѯ���ﳵ
	ShoppingcarPage findShoppingList(int currentPage, int pageSize, int userid);
	
	// �����û��Ͳ˲��ҹ��ﳵ
	Shoppingcar findShopping(User user, Greens greens);
}
