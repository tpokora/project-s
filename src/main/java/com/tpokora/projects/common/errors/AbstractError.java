package com.tpokora.projects.common.errors;

/**
 * Created by pokor on 14.02.2016.
 */
// TODO: Better error propagation
public abstract class AbstractError implements ErrorsInterface {
    protected int code;
    protected ErrorTypes type;
    protected String text;

    public AbstractError() {}

    public AbstractError(ErrorTypes code) {
        this.setError(type);
    }

    public int getCode() {
        return code;
    }

    public ErrorTypes getType() {
        return type;
    }

    public String getText() {
        return text;
    }
}
