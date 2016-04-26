package com.code_embryo.android.ble.beacon.record;

import org.junit.Test;
import org.mockito.internal.matchers.NotNull;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class TxPowerTest {
  @Test
  public void constructor() {
    byte power = -128;
    TxPower txPower = new TxPower(power);

    assertThat(txPower, is(notNullValue()));
  }

  @Test
  public void testValue() throws Exception {
    byte power = -128;
    TxPower txPower = new TxPower(power);

    byte actual = txPower.value();
    byte expected = -128;

    assertThat(actual, is(expected));
  }
}