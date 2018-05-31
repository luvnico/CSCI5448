package beans;

public class Post {
	private int pid;
	private String title, type, desc, c_name, c_email, status, c_date, m_date;
	private Item item;
	private Housing housing;
	//public Date c_date, m_date;
	public Post(){
		this.setPid(-1);
	}
	
	public Post(int pid, String status, String title, String desc, String type, String c_date, String m_date){
		this.setPid(pid);
		this.setType(type);
		this.setTitle(title);
		this.setDesc(desc);
		this.setStatus(status);
		this.setC_date(c_date);
		this.setM_date(m_date);
	}
	
	//public getters & setters
	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	//for contact name
	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}
	//for contact email
	public String getC_email(){
		return c_email;
	}
	
	public void setC_email(String c_email){
		this.c_email = c_email;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getC_date() {
		return c_date;
	}

	public void setC_date(String c_date) {
		this.c_date = c_date;
	}

	public String getM_date() {
		return m_date;
	}

	public void setM_date(String m_date) {
		this.m_date = m_date;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public Housing getHousing() {
		return housing;
	}

	public void setHousing(Housing housing) {
		this.housing = housing;
	}
	
	
}