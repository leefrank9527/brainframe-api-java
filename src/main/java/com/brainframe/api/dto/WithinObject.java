package com.brainframe.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.util.Map;

public class WithinObject {
    private String class_name;
    private Rectangle coords;
    private List<WithinObject> children;
    private Map<String, Object> attributes;
    private Map<String, Object> extra_data;

    private String track_id;
    private String with_identity;

    @JsonIgnore
    public float getConfidence() {
        if (this.extra_data != null && this.extra_data.containsKey("confidence")) {
            return (Float) this.extra_data.get("confidence");
        } else {
            return -1;
        }
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public Rectangle getCoords() {
        return coords;
    }

    public void setCoords(Rectangle coords) {
        this.coords = coords;
    }

    public List<WithinObject> getChildren() {
        return children;
    }

    public void setChildren(List<WithinObject> children) {
        this.children = children;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getExtra_data() {
        return extra_data;
    }

    public void setExtra_data(Map<String, Object> extra_data) {
        this.extra_data = extra_data;
    }

    public String getTrack_id() {
        return track_id;
    }

    public void setTrack_id(String track_id) {
        this.track_id = track_id;
    }

    public String getWith_identity() {
        return with_identity;
    }

    public void setWith_identity(String with_identity) {
        this.with_identity = with_identity;
    }
}
