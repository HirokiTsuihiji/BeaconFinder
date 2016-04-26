package com.code_embryo.android.ble.beacon.data.radio;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class RadioFieldTest {
  @Test
  public void constructor() {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));
    RadioField radioField = new RadioField(intensity);

    assertThat(radioField, is(notNullValue()));
  }

  @Test(expected = NullPointerException.class)
  public void testNullIntensity() {
    new RadioField(null);
  }

  @Test
  public void testRssi() throws Exception {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));
    RadioField radioField = new RadioField(intensity);

    Integer actual = radioField.rssi();
    Integer expected = -128;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTxPower() throws Exception {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));
    RadioField radioField = new RadioField(intensity);

    Integer actual = radioField.txPower();
    Integer expected = -128;

    assertThat(actual, is(expected));
  }

  @Test
  public void testAccuracy() throws Exception {
    Rssi rssi = new Rssi(-128);
    TxPower txPower = new TxPower(-128);
    Intensity intensity = new Intensity(rssi, txPower);
    RadioField radioField = new RadioField(intensity);

    Double actual = radioField.accuracy();
    Integer signalDifference = txPower.value() - rssi.value();
    Double expected = Math.pow(10, signalDifference.doubleValue() / 20.0d);

    assertThat(actual, is(expected));
  }

  @Test
  public void testProximity() throws Exception {
    Intensity intensity = new Intensity(new Rssi(-128), new TxPower(-128));
    RadioField radioField = new RadioField(intensity);

    String actual = radioField.proximity();
    String expected = "Near";

    assertThat(actual, is(expected));
  }
}