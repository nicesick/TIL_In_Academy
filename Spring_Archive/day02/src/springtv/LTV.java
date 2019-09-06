package springtv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ltv")
public class LTV implements TV {
	String status;
	int volume;
	
	@Autowired
	Speaker speaker;
	
	@Override
	public void turnOn() {
		this.status = "LTV ON";
	}

	@Override
	public void turnOff() {
		this.status = "LTV OFF";
	}

	@Override
	public void volumeUp(int v) {
		speaker.up();
	}

	@Override
	public void volumeDown(int v) {
		speaker.down();
	}

	@Override
	public String toString() {
		return "LTV [status=" + status + ", volume=" + volume + "]";
	}
	
}
