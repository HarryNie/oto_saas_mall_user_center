package pecker.utils;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author baiban
 * create by baiban to 2018-05-21
 */
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private ExceptionLevel level;
	private String module;
	private int errorCode;
	private String errorMessage;
	private Map<String, Object> context;

	public ApiException(String errorMessage) {
		super();
		this.level = ExceptionLevel.SLIGHT;
		this.module = "";
		this.errorCode = 500;
		this.errorMessage = errorMessage;
	}

	public ApiException(int code, String errorMessage) {
		super();
		this.level = ExceptionLevel.SLIGHT;
		this.module = "";
		this.errorCode = code;
		this.errorMessage = errorMessage;
	}

//	public ApiException(ReqEnums enums) {
//		super();
//		this.level = ExceptionLevel.SLIGHT;
//		this.module = "";
//		this.errorCode = enums.getCode();
//		this.errorMessage = enums.getMsg();
//	}

	public ApiException(ExceptionLevel level, int code, String errorMessage) {
		super();
		this.level = level;
		this.module = "";
		this.errorCode = code;
		this.errorMessage = errorMessage;
	}


	public ApiException(ExceptionLevel level, String module, int errorCode, String errorMessage, Map<String, Object> context, Throwable cause) {
		super(cause);
		this.level = level;
		this.module = module;
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.context = context;
	}

	public ApiException addContext(String key, Object value) {
		if(this.context == null) {
			this.context = new HashMap();
		}

		this.context.put(key, value);
		return this;
	}

	public ExceptionLevel getLevel() {
		return this.level;
	}

	public void setLevel(ExceptionLevel level) {
		this.level = level;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public int getErrorCode() {
		return this.errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Map<String, Object> getContext() {
		return this.context;
	}

	public enum ExceptionLevel {
		SERIOUS(1, "严重"),
		COMMON(2, "一般"),
		SLIGHT(3, "轻微");

		private final int value;
		private final String description;

		private ExceptionLevel(int value, String description) {
			this.value = value;
			this.description = description;
		}

		public int getValue() {
			return this.value;
		}

		public String getDescription() {
			return this.description;
		}
	}
}

