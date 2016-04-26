package com.code_embryo.android.ble.beacon.record.header;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class AdTypeTest {

  @Test
  public void constructor() {
    byte numberOfType = 127;
    AdType type = new AdType(numberOfType);

    assertThat(type, is(notNullValue()));
  }

  @Test
  public void value() throws Exception {
    byte numberOfType = 0;
    AdType type = new AdType(numberOfType);

    byte actual = type.value();
    byte expected = 0;

    assertThat(actual, is(expected));
  }
}