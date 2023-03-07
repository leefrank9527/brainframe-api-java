package com.brainframe.api.dto;

import java.util.List;
import java.util.Map;

public class ZoneStatus {
    private Zone zone;
    private float tstamp;
    private Map<String, Long> total_entered, total_exited, entering, exiting;

    private List<WithinObject> within;
    private List<Alert> alerts;

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public float getTstamp() {
        return tstamp;
    }

    public void setTstamp(float tstamp) {
        this.tstamp = tstamp;
    }

    public Map<String, Long> getTotal_entered() {
        return total_entered;
    }

    public void setTotal_entered(Map<String, Long> total_entered) {
        this.total_entered = total_entered;
    }

    public Map<String, Long> getTotal_exited() {
        return total_exited;
    }

    public void setTotal_exited(Map<String, Long> total_exited) {
        this.total_exited = total_exited;
    }

    public Map<String, Long> getEntering() {
        return entering;
    }

    public void setEntering(Map<String, Long> entering) {
        this.entering = entering;
    }

    public Map<String, Long> getExiting() {
        return exiting;
    }

    public void setExiting(Map<String, Long> exiting) {
        this.exiting = exiting;
    }

    public List<WithinObject> getWithin() {
        return within;
    }

    public void setWithin(List<WithinObject> within) {
        this.within = within;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
