
public class Vehicle {
	private String make;
	private String model;
	private double mpg;
	
	public Vehicle(String make, String model, double mpg) {
		this.make = make;
		this.model = model;
		setMpg(mpg);
	}
	//getter methods
	public String getMake() {
		return this.make;
	}
	
	public String getModel() {
		return this.model;
	}
	
	public double getMpg() {
		return this.mpg;
	}
	
	//setter method for MPG input validation
    public void setMpg(double mpg) {
        if (mpg > 0 && mpg <= 200) {  
            this.mpg = mpg;
        } else {
            throw new IllegalArgumentException("Invalid MPG value. Must be between 0 and 200.");
        }
    }
	
    @Override
    public String toString() {
        return String.format("%-15s %-15s %5.1f", this.make, this.model, this.mpg);
    }
}
