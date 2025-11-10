package part3.exception;

import part3.code.ErrorCode;

public class ValidationException extends RuntimeException  {
	private static final long serialVersionUID = 1L;
	
	private final ErrorCode code;
	
	public ValidationException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }
	
	public ValidationException(ErrorCode code) {
        super(code.name());
        this.code = code;
    }
	
	public ErrorCode getCode() {
        return code;
    }
}
