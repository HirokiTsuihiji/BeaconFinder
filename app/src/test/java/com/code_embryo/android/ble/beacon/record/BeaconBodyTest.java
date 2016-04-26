package com.code_embryo.android.ble.beacon.record;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconBodyTest {
  @Test
  public void constructor() {
    byte[] uuid = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    byte txPower = -128;

    BeaconBody beaconBody = new BeaconBody(uuid, majorId, minorId, txPower);

    assertThat(beaconBody, is(notNullValue()));
  }

  @Test
  public void testUuid() throws Exception {
    byte[] uuid = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    byte txPower = -128;

    BeaconBody beaconBody = new BeaconBody(uuid, majorId, minorId, txPower);

    byte[] actual = beaconBody.uuid();
    byte[] expected = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };

    assertThat(actual, is(expected));
  }

  @Test
  public void testMajor() throws Exception {
    byte[] uuid = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    byte txPower = -128;

    BeaconBody beaconBody = new BeaconBody(uuid, majorId, minorId, txPower);

    byte[] actual = beaconBody.major();
    byte[] expected = new byte[]{(byte) 0x55, (byte) 0xAA};

    assertThat(actual, is(expected));
  }

  @Test
  public void testMinor() throws Exception {
    byte[] uuid = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    byte txPower = -128;

    BeaconBody beaconBody = new BeaconBody(uuid, majorId, minorId, txPower);

    byte[] actual = beaconBody.minor();
    byte[] expected = new byte[]{(byte) 0xAA, (byte) 0x55};

    assertThat(actual, is(expected));
  }

  @Test
  public void testTxPower() throws Exception {
    byte[] uuid = new byte[]{
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04,
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };
    byte[] majorId = new byte[]{(byte) 0x55, (byte) 0xAA};
    byte[] minorId = new byte[]{(byte) 0xAA, (byte) 0x55};
    byte txPower = -128;

    BeaconBody beaconBody = new BeaconBody(uuid, majorId, minorId, txPower);

    byte actual = beaconBody.txPower();
    byte expected = -128;

    assertThat(actual, is(expected));
  }
}