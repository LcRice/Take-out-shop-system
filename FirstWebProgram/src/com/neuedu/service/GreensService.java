package com.neuedu.service;

import com.neuedu.entity.Greens;
import com.neuedu.entity.Shoppingcar;
import com.neuedu.vo.GreensPage;

public interface GreensService {

	// �ϼܲ�
	boolean addGreens(Greens greens, int restid);

	// ���ݲ�����ѯ��
	boolean checkGreensname(String greensname, int restid);

	Greens findGreens(String greensname, int restid);

	// ���ݲ˱�Ų�ѯ��
	Greens findGreensByGreensid(int greensid);

	// ���²˵ļ۸񣬿�棬��Ƭ
	boolean updateGreens(Greens greens, Greens newgreens);

	// ���²˵�ȫ��
	boolean updateGreensAll(Greens oldgreens, Greens newgreens);

	// ���ݲ˱��ɾ����
	boolean deleteGreensByGreensid(int greensid);

	// ����ɾ��
	boolean deleteGreensBatch(String[] greensids);

	// ������ҳ��ѯ��
	GreensPage getGreensPage(int currentPage, int pageSize, int restid);

	// ��ȡ���˱��
	int getMaxGreensid();

	// �û���ҳ��ѯ��
	GreensPage getGreensPage(int currentPage, int pageSize, String greensname);

	// ���¿��
	boolean updateGreensnumber(Greens greens, Shoppingcar shoppingcar);

	// ����������
	boolean updateGreencommentcount(int greensid);
}
