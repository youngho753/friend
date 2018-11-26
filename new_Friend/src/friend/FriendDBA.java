package friend;


import java.util.ArrayList;

import javax.swing.JTextField;

public interface FriendDBA {
	//추�?
	public void friendInsert(Friend f);
	//보기
	public ArrayList<Friend> friendView();
	//�??��
	public ArrayList<Friend> friendSearch(int type,String str);
	//?��?��
	public void friendUpdate(int num,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4);
	//?��?��
	public void friendDelete(int num);
	//?��?��보기
	public Friend friendDetail(int num);
}
