package springtv;

public class HarmanSpeaker implements Speaker {

	@Override
	public void up() {
		System.out.println("Harman Speaker Up");
	}

	@Override
	public void down() {
		System.out.println("Harman Speaker Down");
	}
}
