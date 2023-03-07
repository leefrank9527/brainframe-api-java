package com.brainframe.api.dto;


public class ProcessVideoRequest {
    private Long stream_id;
    private String storage_uri;
    private String ai_interval_type;
    private Float ai_interval_val;
    private Float timeout;

    public Long getStream_id() {
        return stream_id;
    }

    public void setStream_id(Long stream_id) {
        this.stream_id = stream_id;
    }

    public String getStorage_uri() {
        return storage_uri;
    }

    public void setStorage_uri(String storage_uri) {
        this.storage_uri = storage_uri;
    }

    public String getAi_interval_type() {
        return ai_interval_type;
    }

    public void setAi_interval_type(String ai_interval_type) {
        this.ai_interval_type = ai_interval_type;
    }

    public Float getAi_interval_val() {
        return ai_interval_val;
    }

    public void setAi_interval_val(Float ai_interval_val) {
        this.ai_interval_val = ai_interval_val;
    }

    public Float getTimeout() {
        return timeout;
    }

    public void setTimeout(Float timeout) {
        this.timeout = timeout;
    }
}
