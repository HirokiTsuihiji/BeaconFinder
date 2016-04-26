package com.code_embryo.android.ble.beacon.record;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AttributeIdTest {
@Test
  public void constructor() {
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA };
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    AttributeId attributeId = new AttributeId(majorId, minorId);

    assertThat(attributeId, is(notNullValue()));
  }
  @Test
  public void testMajor() throws Exception {
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    AttributeId attributeId = new AttributeId(majorId, minorId);

    byte[] actual = attributeId.major();
    byte[] expected = new byte[]{(byte) 0x55, (byte) 0xAA};

    assertThat(actual, is(expected));
  }

  @Test
  public void testMinor() throws Exception {
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    AttributeId attributeId = new AttributeId(majorId, minorId);

    byte[] actual = attributeId.minor();
    byte[] expected = new byte[]{(byte) 0xAA, (byte) 0x55};

    assertThat(actual, is(expected));
  }
}