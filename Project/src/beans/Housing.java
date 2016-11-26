package beans;

public class Housing {
	private String category;
	private int area;	
	private Boolean catOk;
	private Boolean dogOk;
	
	public Housing(){	
		
	}
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getArea() {
		return area;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public Boolean getCatOk() {
		return catOk;
	}
	public void setCatOk(Boolean catOk) {
		this.catOk = catOk;
	}
	public Boolean getDogOk() {
		return dogOk;
	}
	public void setDogOk(Boolean dogOk) {
		this.dogOk = dogOk;
	}
	
	
}
