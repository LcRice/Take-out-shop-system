package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;

public interface GreensDAO {
	//�ϼܲ�
	boolean insertGreens(Greens greens,int restid);
	
	//���ݲ�����ѯ��
	Greens checkGreensname(String greensname,int restid);
	
	//���ݲ˱�Ų�ѯ
	Greens findGreensByGreensid(int greensid);
	
	//���²˵Ŀ�棬�۸���Ƭ---�ϼ�
	boolean updateGreens(Greens greens,Greens newgreens);

	//���²˵���Ϣ---�޸�
	boolean updateGreensAll(Greens oldgreens,Greens newgreens);
	
	//ɾ����
	boolean deleteGreensByGreensid(int greensid);
	
	//����ɾ��
	boolean deleteGreensBatch(String[] greensids);
	
	//������ҳ��ѯ���в�
	List<Greens> findGreensList(int currentPage, int pageSize,int restid);

	int findTotalCount(int restid);
	
	//��ȡ�������
	int findMaxGreensid();
	
	//�û���ҳ��ѯ���в�
	List<Greens> findGreensList(int currentPage, int pageSize, String greensname);
	
	int findTotalCount(String greensname);
	
	//���¿��
	boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar);
	
	// ����������
	boolean updateGreencommentcount(int greensid);
}
