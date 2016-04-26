package com.code_embryo.android.ble.beacon.data.radio;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class IntensityTest {
  @Test
  public void constructor() {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));

    assertThat(intensity, is(notNullValue()));
  }

  @Test(expected = NullPointerException.class)
  public void testNullRssi() {
    new Intensity(null, new TxPower(-128));
  }

  @Test(expected = NullPointerException.class)
  public void testNullTxPower() {
    new Intensity(new Rssi(-128), null);
  }

  @Test
  public void testRssi() throws Exception {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));

    Integer actual = intensity.rssi();
    Integer expected = -128;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTxPower() throws Exception {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));

    Integer actual = intensity.txPower();
    Integer expexted = -128;

    assertThat(actual, is(expexted));
  }
}