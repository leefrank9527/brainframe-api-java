package com.brainframe.api.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class Zone {
    private Long id;
    private String name;
    private Long streamId;
    private List<Alarm> alarms = new ArrayList<>();

    private List<Coord> coords;

    @JsonIgnore
    public boolean isScreen() {
        return coords == null || coords.size() == 0;
    }

    @JsonIgnore
    public boolean isLine() {
        return coords != null && coords.size() == 2;
    }

    @JsonIgnore
    public boolean isArea() {
        return coords != null && coords.size() > 2;
    }

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

    public Long getStreamId() {
        return streamId;
    }

    public void setStreamId(Long streamId) {
        this.streamId = streamId;
    }

    public List<Alarm> getAlarms() {
        return alarms;
    }

    public void setAlarms(List<Alarm> alarms) {
        this.alarms = alarms;
    }

    public List<Coord> getCoords() {
        return coords;
    }

    public void setCoords(List<Coord> coords) {
        this.coords = coords;
    }
}
