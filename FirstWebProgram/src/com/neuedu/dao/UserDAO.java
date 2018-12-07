package com.neuedu.dao;

import java.util.List;

import com.neuedu.entity.Orderdetail;
import com.neuedu.entity.User;

public interface UserDAO {
	// �û�ע��
	boolean insertUser(User user);

	// ����usernumber�����û�---ע��
	User checkUsernumber(String usernumber);

	// �����û����������������û�---��¼
	User findUser(String usernumber, String userpassword);

	// �����û���Ϣ
	boolean updateUser(User olduser, User newuser);

	// ����ɹ������
	boolean updateUser(User user, int price);

	// ��ҳ�鿴�����û�
	List<User> findUserList(int currentPage, int pageSize);

	int findTotalCount();

	// ����Userid�����û�
	User findUser(int userid);

	// ��ҳ�鿴�û����ж���
	List<Orderdetail> findOrderList(int currentPage, int pageSize, int userid);

	int findTotalCount(int userid);
}
