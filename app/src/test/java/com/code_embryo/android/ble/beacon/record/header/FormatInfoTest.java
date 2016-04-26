package com.code_embryo.android.ble.beacon.record.header;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FormatInfoTest {

  @Test
  public void constructor() {
    byte[] info = new byte[]{(byte)0xFF, (byte)0x00, (byte)0x80};
    FormatInfo formatInfo = new FormatInfo(info);

    assertThat(formatInfo, is(notNullValue()));
  }

  @Test
  public void testValue() throws Exception {
    byte[] info = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x80};
    FormatInfo formatInfo = new FormatInfo(info);

    byte[] actual = formatInfo.value();
    byte[] expected = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x80};

    assertThat(actual, is(expected));
  }
}