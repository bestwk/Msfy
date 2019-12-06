package com.ruoyi.common.core.domain;


/**
 * http请求结果的封装类
 */
public class ResponseData<T> {
    public final static String MESSAGE_OK="操作成功！";
    public final static String MESSAGE_ERROR="操作失败！";
    private boolean success;
    private String message;
    private T data;

    public ResponseData(){
        this.success=false;
        this.message="";
        this.data=null;
    }

    public ResponseData(boolean isSuccess, String message, T data){
        this.success=isSuccess;
        this.message=message;
        this.data=data;
    }

    public ResponseData(boolean isSuccess, T data){
        this.success=isSuccess;
        this.message=isSuccess?ResponseData.MESSAGE_OK:ResponseData.MESSAGE_ERROR;
        this.data=data;
    }

    public ResponseData(boolean isSuccess){
        this.success=isSuccess;
        this.message=isSuccess?ResponseData.MESSAGE_OK:ResponseData.MESSAGE_ERROR;
        this.data=null;
    }

    /**
     * 是否成功请求到数据
     *
     * @return the boolean
     * @author: 任向阳
     * @date: 2018年09月21日 11:55
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 请求成功或失败时的消息提示
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message.
     *
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 要返回的数据
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }
}
