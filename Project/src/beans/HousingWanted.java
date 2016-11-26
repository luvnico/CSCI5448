package beans;

public class HousingWanted extends Housing{
	
	private int lowerBound;
	private int higherBound;
	
	public int getLowerBound() {
		return lowerBound;
	}

	public void setLowerBound(int lowerBound) {
		this.lowerBound = lowerBound;
	}

	public int getHigherBound() {
		return higherBound;
	}

	public void setHigherBound(int higherBound) {
		this.higherBound = higherBound;
	}

	public Housing editHousing(Housing housing, String category, int area, Boolean catOk,
			Boolean dogOk, int lowerBound, int higherBound){
		
		housing.setCategory(category);
		housing.setArea(area);
		housing.setCatOk(catOk);
		housing.setDogOk(dogOk);
		this.lowerBound = lowerBound;
		this.higherBound = higherBound;
		
		return housing;
		
	}

}
