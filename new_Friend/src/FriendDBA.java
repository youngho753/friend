

import java.util.ArrayList;

import javax.swing.JTextField;

public interface FriendDBA {
	//Ï∂îÍ?
	public void friendInsert(Friend f);
	//Î≥¥Í∏∞
	public ArrayList<Friend> friendView();
	//Í≤??Éâ
	public ArrayList<Friend> friendSearch(int type,String str);
	//?àò?†ï
	public void friendUpdate(int num,JTextField tf1,JTextField tf2,JTextField tf3,JTextField tf4);
	//?Ç≠?†ú
	public void friendDelete(int num);
	//?ÉÅ?Ñ∏Î≥¥Í∏∞
	public Friend friendDetail(int num);
}
