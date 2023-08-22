package it.blog.rabbitmq.message;

public class WeatherForecast {

	private double temp;
	private double windSpeed;
	private String description;
	public double getTemp() {
		return temp;
	}
	public void setTemp(double temp) {
		this.temp = temp;
	}
	public double getWindSpeed() {
		return windSpeed;
	}
	public void setWindSpeed(double windSpeed) {
		this.windSpeed = windSpeed;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "WeatherForecast [temp=" + temp + ", windSpeed=" + windSpeed + ", description=" + description + "]";
	}
}
