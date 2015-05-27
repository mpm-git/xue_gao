package org.cz.project.entity.bean;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

public class Result<T> {

	@Expose
	private String status;
	@Expose
	private String message;
	@Expose
	private T result;
	
	private T total;
	
	
	

	public T getTotal() {
		return total;
	}
	public void setTotal(T total) {
		this.total = total;
	}
	public Result(String status, String message, T result, T total) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
		this.total = total;
	}
	public Result() {
		super();
	}
	public Result(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public Result(String status, String message, T result) {
		super();
		this.status = status;
		this.message = message;
		this.result = result;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getResult() {
		return result;
	}
	public void setResult(T result) {
		this.result = result;
	}
	public String toJson()
	{
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").excludeFieldsWithoutExposeAnnotation().create();
		if(this.getResult() instanceof String)
		{
			StringBuffer buffer = new StringBuffer();
			buffer.append("{\"status\":\"")
			.append(status)
			.append("\",\"message\":")
			.append(gson.toJson(message));
			if(result!=null)
			{
				buffer.append(",\"result\":")
				.append(result);
			}
			buffer.append("}");
			return buffer.toString();
		}
		return gson.toJson(this);
	}
	@Override
	public String toString() {
		return "Result [message=" + message + ", status=" + status
				+ ", result=" + result + "]";
	}
}
