package com.code_embryo.android.ble.beacon.data.radio;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ProximityTest {
  @Test
  public void constructor() {
    Intensity intensity = new Intensity(new Rssi(-32), new TxPower(-64));
    Proximity proximity = new Proximity(intensity);

    assertThat(proximity, is(notNullValue()));
  }

  @Test
  public void testAccuracy() throws Exception {
    Rssi rssi = new Rssi(-32);
    TxPower txPower = new TxPower(-64);
    Intensity intensity = new Intensity(rssi, txPower);
    Proximity proximity = new Proximity(intensity);

    Double actual = proximity.accuracy();
    Integer signalDifference = txPower.value() - rssi.value();
    Double expected = Math.pow(10, signalDifference.doubleValue() / 20.0d);

    assertThat(actual, is(expected));
  }

  @Test
  public void testValueImmediate() throws Exception {
    Rssi rssi = new Rssi(-32);
    TxPower txPower = new TxPower(-64);
    Intensity intensity = new Intensity(rssi, txPower);
    Proximity proximity = new Proximity(intensity);

    String actual = proximity.value();
    String expected = "Immediate";

    assertThat(actual, is(expected));
  }

  @Test
  public void testValueNear() {
    Rssi rssi = new Rssi(-64);
    TxPower txPower = new TxPower(-64);
    Intensity intensity = new Intensity(rssi, txPower);
    Proximity proximity = new Proximity(intensity);

    String actual = proximity.value();
    String expected = "Near";

    assertThat(actual, is(expected));
  }

  @Test
  public void testValueFar() {
    Rssi rssi = new Rssi(-80);
    TxPower txPower = new TxPower(-64);
    Intensity intensity = new Intensity(rssi, txPower);
    Proximity proximity = new Proximity(intensity);

    String actual = proximity.value();
    String expected = "Far";

    assertThat(actual, is(expected));
  }
}