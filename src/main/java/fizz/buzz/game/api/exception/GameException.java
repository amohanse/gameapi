package fizz.buzz.game.api.exception;

public class GameException extends RuntimeException {

	public GameException() {
	}

	public GameException(String s) {
		super(s);
	}

	public GameException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public GameException(Throwable throwable) {
		super(throwable);
	}

	private static final long serialVersionUID = 1L;
}
