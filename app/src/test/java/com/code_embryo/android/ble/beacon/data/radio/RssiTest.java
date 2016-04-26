package com.code_embryo.android.ble.beacon.data.radio;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RssiTest {
  @Test
  public void constructor() {
    Rssi rssi = new Rssi(0);

    assertThat(rssi, is(notNullValue()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnderRange() {
    new Rssi(-129);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverRange() {
    new Rssi(128);
  }

  @Test
  public void testValue() throws Exception {
    Rssi rssi = new Rssi(0);

    Integer actual = rssi.value();
    Integer expected = 0;

    assertThat(actual, is(expected));
  }
}