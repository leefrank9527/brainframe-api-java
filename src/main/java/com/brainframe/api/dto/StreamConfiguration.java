package com.brainframe.api.dto;

import java.util.Map;

public class StreamConfiguration {
    private Long id;
    private String name;
    private Long premises_id;
    private String connection_type;
    private Map<String,Object> connection_options;
    private Map<String,Object> runtime_options;

    private Map<String,Object> metadata;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPremises_id() {
        return premises_id;
    }

    public void setPremises_id(Long premises_id) {
        this.premises_id = premises_id;
    }

    public String getConnection_type() {
        return connection_type;
    }

    public void setConnection_type(String connection_type) {
        this.connection_type = connection_type;
    }

    public Map<String, Object> getConnection_options() {
        return connection_options;
    }

    public void setConnection_options(Map<String, Object> connection_options) {
        this.connection_options = connection_options;
    }

    public Map<String, Object> getRuntime_options() {
        return runtime_options;
    }

    public void setRuntime_options(Map<String, Object> runtime_options) {
        this.runtime_options = runtime_options;
    }

    public Map<String, Object> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
    }
}