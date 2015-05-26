package org.cz.project.dao;

/**
 * @author huang.zhengyu@wuxi.stee.stengg.com.cn
 * @version 1.0
 * @date 2014-5-7
 */
public class GenericDaoException extends Exception {

	private String errorMsg;

	public GenericDaoException() {
	}

	public GenericDaoException(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

}
