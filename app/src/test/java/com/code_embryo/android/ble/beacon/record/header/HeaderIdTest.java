package com.code_embryo.android.ble.beacon.record.header;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HeaderIdTest {

  @Test
  public void contructor() {
    byte[] companyId = new byte[]{(byte)0xFF, (byte)0x00, (byte)0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    HeaderId headerId = new HeaderId(companyId, formatInfo);

    assertThat(headerId, is(notNullValue()));
  }

  @Test
  public void testCompanyId() throws Exception {
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    HeaderId headerId = new HeaderId(companyId, formatInfo);

    byte[] actual = headerId.companyId();
    byte[] expected = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};

    assertThat(actual, is(expected));
  }

  @Test
  public void testBeaconFormatInfo() throws Exception {
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    HeaderId headerId = new HeaderId(companyId, formatInfo);

    byte[] actual = headerId.beaconFormatInfo();
    byte[] expected = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};

    assertThat(actual, is(expected));
  }
}