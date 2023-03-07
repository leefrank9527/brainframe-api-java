package com.brainframe.api;

import com.brainframe.api.dto.StreamConfiguration;
import com.brainframe.api.dto.ZoneStatuses;
import com.brainframe.api.stub.StubProcessVideo;
import com.brainframe.api.stub.StubStreamConfiguration;
import com.brainframe.api.stub.ZoneStatusCallback;
import org.junit.Test;

public class TestStubProcessVideo implements TestBaseStub {
    private final String aiIntervalType = "time";
    private final Float aiIntervalVal = 1.0F;
    private final Float timeout = 300.0F;
    private final StubProcessVideo testInstance = new StubProcessVideo(baseUrl, bfIP, aiIntervalType, aiIntervalVal, timeout);

    @Test
    public void testStartProcessVide() {
        StubStreamConfiguration streamApi = new StubStreamConfiguration(baseUrl, bfIP);

        StreamConfiguration sc = new StreamConfiguration();
        sc.setName("Test");
        sc.setConnection_type("virtual");

        sc = streamApi.setStreamConfiguration(sc);

        assert sc != null;
        assert sc.getId() != null;

        String storage_uri = "sftp://leefr:leefr@192.168.1.65//home/leefr/projects/video_qa/videos/2M.mp4";
        ZoneStatusParse callback = new ZoneStatusParse();
        boolean ret = testInstance.startProcessVideo(sc.getId(), storage_uri, callback);
        assert ret;
    }
}

class ZoneStatusParse implements ZoneStatusCallback {
    private Long sessionId = null;

    @Override
    public void receiveZoneStatus(ZoneStatuses zoneStatuses) {
        sessionId = 1L;
    }
}