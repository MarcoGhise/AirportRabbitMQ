package it.blog.rabbitmq.message;

public class PilotCommunication {

	private String pilot;
	private String message;
	public String getPilot() {
		return pilot;
	}
	public void setPilot(String pilot) {
		this.pilot = pilot;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "PilotCommunication [pilot=" + pilot + ", message=" + message + "]";
	}
}
