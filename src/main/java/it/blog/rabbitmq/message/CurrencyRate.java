package it.blog.rabbitmq.message;

public class CurrencyRate {

	private String flightCode;
	private CurrencyType currency;
	public String getFlightCode() {
		return flightCode;
	}
	public void setFlightCode(String flightCode) {
		this.flightCode = flightCode;
	}
	public CurrencyType getCurrency() {
		return currency;
	}
	public void setCurrency(CurrencyType currency) {
		this.currency = currency;
	}
	@Override
	public String toString() {
		return "CurrencyRate [flightCode=" + flightCode + ", currency=" + currency + "]";
	}
}
