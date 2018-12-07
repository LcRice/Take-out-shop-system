package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Restaurant;
import com.neuedu.vo.OrderPage;
import com.neuedu.vo.RestPage;

public interface RestService {
	// �̻�ע��
	boolean addRest(Restaurant rest);

	// ����restnumber�����̻�
	boolean checkRestnumber(String restnumber);

	// �����û����������������û�---��¼,�鿴������Ϣ
	Restaurant login(String restnumber, String restpassword);

	// �����̻�
	boolean updateRest(Restaurant oldrest, Restaurant newrest);

	// ���²������
	boolean updateRestamount(Greens greens);

	// ������ҳ��ѯ����
	OrderPage getOrdersPage(int currentPage, int pageSize, int restid);

	// ����������
	boolean updateRestcommentcount(int restid);

	// ��ҳ��ʾ����
	RestPage getRestPage(int currentPage, int pageSize);

	// ����id�����̻�
	Restaurant findRest(int restid);
}
