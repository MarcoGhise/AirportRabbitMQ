package it.blog.rabbitmq.message;

public class Passenger {

	private String name;
	private String surname;
	private String flightNumber;
	private String seat;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSeat() {
		return seat;
	}
	public void setSeat(String seat) {
		this.seat = seat;
	}
	@Override
	public String toString() {
		return "Passenger [name=" + name + ", surname=" + surname + ", flightNumber=" + flightNumber + ", seat=" + seat
				+ "]";
	}
}
