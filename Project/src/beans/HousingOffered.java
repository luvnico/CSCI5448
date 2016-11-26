package beans;

import java.util.Date;

public class HousingOffered extends Housing{
	private int price;
	private Date availableDate;
	private Boolean isFurnished;
	private Boolean noSmoking;
	private float numOfBed;
	private float numOfBath;
	private String parkingOpt;
	private String laundryOpt;
	
	
	public int getPrice() {
		return price;
	}


	public void setPrice(int price) {
		this.price = price;
	}


	public Boolean getIsFurnished() {
		return isFurnished;
	}


	public void setIsFurnished(Boolean isFurnished) {
		this.isFurnished = isFurnished;
	}


	public Boolean getNoSmoking() {
		return noSmoking;
	}


	public void setNoSmoking(Boolean noSmoking) {
		this.noSmoking = noSmoking;
	}


	public float getNumOfBed() {
		return numOfBed;
	}


	public void setNumOfBed(float numOfBed) {
		this.numOfBed = numOfBed;
	}


	public float getNumOfBath() {
		return numOfBath;
	}


	public void setNumOfBath(float numOfBath) {
		this.numOfBath = numOfBath;
	}


	public String getParkingOpt() {
		return parkingOpt;
	}


	public void setParkingOpt(String parkingOpt) {
		this.parkingOpt = parkingOpt;
	}


	public String getLaundryOpt() {
		return laundryOpt;
	}


	public void setLaundryOpt(String laundryOpt) {
		this.laundryOpt = laundryOpt;
	}


	public Housing editHousing(Housing housing, String category, int area, Boolean catOk,
			Boolean dogOk, int price, Date availableDate, Boolean isFurnished, Boolean noSmoking,
			float numOfBed, float numofBath, String parkingOpt, String laundryOpt){
		
		housing.setCategory(category);
		housing.setArea(area);
		housing.setCatOk(catOk);
		housing.setDogOk(dogOk);
		this.price = price;
		this.availableDate = availableDate;
		this.isFurnished = isFurnished;
		this.noSmoking = noSmoking;
		this.numOfBed = numOfBed;
		this.numOfBath = numofBath;
		this.parkingOpt =  parkingOpt;
		this.laundryOpt = laundryOpt;
			
		return housing;
		
	}


}
