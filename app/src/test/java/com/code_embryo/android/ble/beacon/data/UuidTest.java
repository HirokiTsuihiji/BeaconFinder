package com.code_embryo.android.ble.beacon.data;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class UuidTest {
  @Test
  public void constructor() {
    Uuid uuid = new Uuid("12345678901234567890123456789012");

    assertThat(uuid, is(notNullValue()));
  }

  @Test
  public void testValue() throws Exception {
    Uuid uuid = new Uuid("12345678901234567890123456789012");

    String actual = uuid.value();
    String expected = "12345678901234567890123456789012";

    assertThat(actual, is(expected));
  }
}