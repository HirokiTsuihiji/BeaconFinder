package com.code_embryo.android.ble.beacon.record.header;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class HeaderTest {

  @Test
  public void constructor() {
    byte adType = (byte) 0x00;
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    Header header = new Header(adType, companyId, formatInfo);

    assertThat(header, is(notNullValue()));
  }

  @Test
  public void testAdType() throws Exception {
    byte adType = (byte) 0x00;
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    Header header = new Header(adType, companyId, formatInfo);

    byte actual = header.adType();
    byte expexted = 0x00;

    assertThat(actual, is(expexted));
  }

  @Test
  public void testCompanyId() throws Exception {
    byte adType = (byte) 0x00;
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    Header header = new Header(adType, companyId, formatInfo);

    byte[] actual = header.companyId();
    byte[] expexted = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};

    assertThat(actual, is(expexted));
  }

  @Test
  public void testBeaconFormatInfo() throws Exception {
    byte adType = (byte) 0x00;
    byte[] companyId = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    byte[] formatInfo = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};
    Header header = new Header(adType, companyId, formatInfo);

    byte[] actual = header.beaconFormatInfo();
    byte[] expexted = new byte[]{(byte) 0xFF, (byte) 0x00, (byte) 0x7F};

    assertThat(actual, is(expexted));
  }
}