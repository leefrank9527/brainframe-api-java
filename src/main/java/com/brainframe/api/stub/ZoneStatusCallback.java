package com.brainframe.api.stub;

import com.brainframe.api.dto.ZoneStatuses;

public interface ZoneStatusCallback {
    void receiveZoneStatus(ZoneStatuses zoneStatuses);
}
