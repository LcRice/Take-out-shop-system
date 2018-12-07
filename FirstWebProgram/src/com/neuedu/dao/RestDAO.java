package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.Restaurant;

public interface RestDAO {
	// �̻�ע��
	boolean insertRest(Restaurant rest);

	// ����restnumber�����̻�
	Restaurant checkRestnumber(String restnumber);
	
	//�����û����������������û�---��¼
	Restaurant findRest(String restnumber,String restpassword);
	
	//�޸Ĳ�����Ϣ
	boolean updateRest(Restaurant oldrest,Restaurant newrest);
	
	//���²������
	boolean updateRestamount(Greens greens);
	
	//�鿴����
	List<Orderdetail> findOrderList(int currentPage, int pageSize,int restid);
	
	int findTotalCount(int restid);
	
	// ����������
	boolean updateRestcommentcount(int restid);
	
	// ��ҳ��ʾ����
	List<Restaurant> findRestList(int currentPage, int pageSize);
	
	int findTotalCount();
	
	//����id�����̻�
	Restaurant findRest(int restid);
}
