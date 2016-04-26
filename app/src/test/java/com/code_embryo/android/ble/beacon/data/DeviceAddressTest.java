package com.code_embryo.android.ble.beacon.data;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class DeviceAddressTest {
  @Test
  public void constructor() {
    DeviceAddress address = new DeviceAddress("00:11:22:33:44:55");

    assertThat(address, is(notNullValue()));
  }

  @Test(expected = NullPointerException.class)
  public void testNullAddress() {
    new DeviceAddress(null);
  }

  @Test
  public void testValue() throws Exception {
    String address = "00:11:22:33:44:55";
    DeviceAddress deviceAddress = new DeviceAddress(address);

    String actual = deviceAddress.value();
    String expected = address;

    assertThat(actual, is(expected));
  }
}