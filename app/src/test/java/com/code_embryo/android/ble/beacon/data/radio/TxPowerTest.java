package com.code_embryo.android.ble.beacon.data.radio;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TxPowerTest {
  @Test
  public void constructor() {
    TxPower txPower = new TxPower(0);

    assertThat(txPower, is(notNullValue()));
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUnderRange() {
    new TxPower(-129);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testOverRange() {
    new TxPower(128);
  }

  @Test
  public void testValue() throws Exception {
    TxPower txPower = new TxPower(0);

    Integer actual = txPower.value();
    Integer expected = 0;

    assertThat(actual, is(expected));
  }
}