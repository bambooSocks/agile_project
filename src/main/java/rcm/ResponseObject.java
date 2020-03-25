package rcm;

@Deprecated
public class ResponseObject {
	int errorCode;
	String errorMessage;
	
	public ResponseObject(int errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	
	
}
