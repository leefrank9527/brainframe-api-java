package com.brainframe.api;

import com.brainframe.api.dto.StreamConfiguration;
import com.brainframe.api.stub.StubStreamConfiguration;
import org.junit.Test;

import java.util.HashMap;

public class TestStreamConfiguration {
    private final StubStreamConfiguration testInstance = new StubStreamConfiguration("http://localhost:8080", "http://localhost:8080");

    @Test
    public void testSetStreamConfiguration() {
        StreamConfiguration sc = new StreamConfiguration();
        sc.setName("Test");
        sc.setConnection_type("virtual");
        sc.setConnection_options(new HashMap<String, Object>());
        sc.setRuntime_options(new HashMap<String, Object>());
        sc.setMetadata(new HashMap<String, Object>());

        sc = testInstance.setStreamConfiguration(sc);

        assert sc.getId() != null;
    }
}
