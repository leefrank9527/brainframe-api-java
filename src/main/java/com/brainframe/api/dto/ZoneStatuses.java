package com.brainframe.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.HashMap;
import java.util.Map;

public class ZoneStatuses {
    private Long stream_id;
    private Long session_id;

    private Map<String, ZoneStatus> zoneStatuses = new HashMap<>();

    @JsonIgnore
    public void putZoneStatus(String zoneStatusName, ZoneStatus zoneStatus) {
        this.zoneStatuses.put(zoneStatusName, zoneStatus);
    }

    public Long getStream_id() {
        return stream_id;
    }

    public void setStream_id(Long stream_id) {
        this.stream_id = stream_id;
    }

    public Long getSession_id() {
        return session_id;
    }

    public void setSession_id(Long session_id) {
        this.session_id = session_id;
    }

    public Map<String, ZoneStatus> getZoneStatuses() {
        return zoneStatuses;
    }

    public void setZoneStatuses(Map<String, ZoneStatus> zoneStatuses) {
        this.zoneStatuses = zoneStatuses;
    }
}
