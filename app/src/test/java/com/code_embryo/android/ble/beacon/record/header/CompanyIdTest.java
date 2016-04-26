package com.code_embryo.android.ble.beacon.record.header;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class CompanyIdTest {

  @Test
  public void contructor() {
    byte[] id = new byte[]{(byte) 0xFF, (byte) 0x80};
    CompanyId companyId = new CompanyId(id);

    assertThat(companyId, is(notNullValue()));
  }

  @Test
  public void value() throws Exception {
    byte[] id = new byte[]{(byte) 0xFF, (byte)0x80};
    CompanyId companyId = new CompanyId(id);

    byte[] actual = companyId.value();
    byte[] expected = new byte[]{(byte) 0xFF, (byte) 0x80};

    assertThat(actual, is(expected));
  }
}