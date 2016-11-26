package beans;

public class Posting {
	private String title = "";
	private String type = "";
	
	private String description = "";
	
	private Item item;
	
	private Housing housing;
	
	
	public Posting(){
		
	}

	public Posting(String title, String type, String description){
		this.title = title;
		this.type = type;
		this.description = description;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void addItem(Object obj, Item item){
		Posting posting = (Posting)obj;
		posting.item = item;
		
	}
	
	public void addHousing(Object obj, Housing housing){
		Posting posting = (Posting)obj;
		posting.housing = housing;
		
	}
	
	
}
