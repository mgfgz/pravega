/**
 * Copyright (c) Dell Inc., or its subsidiaries. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 */
package io.pravega.segmentstore.storage;

import io.pravega.test.common.AssertExtensions;
import java.time.Duration;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests the WriteSettings class.
 */
public class WriteSettingsTests {
    @Test
    public void testConstructor() {
        AssertExtensions.assertThrows(
                "negative maxWriteLength",
                () -> new WriteSettings(-1, Duration.ofMillis(1), 1),
                ex -> ex instanceof IllegalArgumentException);
        AssertExtensions.assertThrows(
                "negative maxWriteTimeout",
                () -> new WriteSettings(1, Duration.ofMillis(-1), 1),
                ex -> ex instanceof IllegalArgumentException);
        AssertExtensions.assertThrows(
                "negative maxOutstandingBytes",
                () -> new WriteSettings(1, Duration.ofMillis(1), -1),
                ex -> ex instanceof IllegalArgumentException);

        // Verify valid args work.
        val ws = new WriteSettings(1, Duration.ofMillis(2), 3);
        Assert.assertEquals(1, ws.getMaxWriteLength());
        Assert.assertEquals(2, ws.getMaxWriteTimeout().toMillis());
        Assert.assertEquals(3, ws.getMaxOutstandingBytes());
        ws.toString();
    }
}
