package com.yibu.web.dto;

import com.wang.constants.Constants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@ApiModel
@NoArgsConstructor
@Getter @Setter
public class HttpResult<T> implements Serializable {

	@ApiModelProperty("是否成功")
	private boolean success;

	@ApiModelProperty("返回结果")
	private T result;

	@ApiModelProperty("响应时间")
	private Long timestamp;

	@ApiModelProperty("错误码")
	private String errorCode;

	@ApiModelProperty("错误消息")
	private String error;

	// 错误消息的参数，用半角逗号隔开
	@ApiModelProperty(hidden = true)
	private String[] errorArgs;

	public static <T> HttpResult<T> error(String errorCode) {
		return new HttpResult<>(null, false, Constants.EMPTY, errorCode, null);
	}

	public static <T> HttpResult<T> errorMsg(String errorCode, String errorMsg) {
		return new HttpResult<>(null, false, errorMsg, errorCode, null);
	}

	public static <T> HttpResult<T> errorMsg(String errorCode, String errorMsg, String[] errorArgs) {
		return new HttpResult<>(null, false, errorMsg, errorCode, errorArgs);
	}

	public static <T> HttpResult<T> success(T result) {
		return new HttpResult<>(result, true, Constants.EMPTY, Constants.EMPTY, null);
	}

	public static <T> HttpResult<T> success() {
		return new HttpResult<>(null, true, Constants.EMPTY, Constants.EMPTY, null);
	}

	private HttpResult(T result, Boolean success, String error, String errorCode, String[] errorArgs) {
		this.success = success;
		this.result = result;
		this.error = error;
		this.errorCode = errorCode;
		this.errorArgs = errorArgs;
		this.timestamp = System.currentTimeMillis();
	}

}
