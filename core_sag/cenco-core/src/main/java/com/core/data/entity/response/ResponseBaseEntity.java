package com.core.data.entity.response;

/**
 * Created by carlos on 07-05-18.
 */

public class ResponseBaseEntity<T> {
    public String internalCode;
    public String message;
    public T payload;
}