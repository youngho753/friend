

public class Friend {
	private int num;
	private String name;
	private String birth;
	public Friend() {
		
	}
	public Friend(int num, String name, String birth, String phone, String addr) {
		this.num = num;
		this.name = name;
		this.birth = birth;
		this.phone = phone;
		this.addr = addr;
	}
	private String phone;
	private String addr;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
}
