package com.brainframe.api;

import com.brainframe.api.dto.StreamConfiguration;
import com.brainframe.api.stub.StubStreamConfiguration;
import org.junit.Test;


import java.util.List;

public class TestStreamConfiguration implements TestBaseStub {
    private final StubStreamConfiguration testInstance = new StubStreamConfiguration(baseUrl, bfIP);

    @Test
    public void testSetStreamConfiguration() {
        StreamConfiguration sc = new StreamConfiguration();
        sc.setName("Test");
        sc.setConnection_type("virtual");

        sc = testInstance.setStreamConfiguration(sc);

        assert sc.getId() != null;
    }

    @Test
    public void testGetStreamConfiguration() {
        long streamId = 33;
        StreamConfiguration sc = testInstance.getStreamConfiguration(streamId);
        assert sc != null;
        assert sc.getId() == streamId;
    }

    @Test
    public void testGetStreamConfigurations() {
        List<StreamConfiguration> scList = testInstance.getStreamConfigurations();
        assert scList != null;
        assert scList.size() > 0;
    }

    @Test
    public void testDeleteStreamConfiguration() {
        long streamId = 33;
        testInstance.deleteStreamConfiguration(streamId);
        StreamConfiguration sc = testInstance.getStreamConfiguration(streamId);
        assert sc == null;
    }
}
