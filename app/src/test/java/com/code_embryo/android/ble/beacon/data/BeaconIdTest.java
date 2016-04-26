package com.code_embryo.android.ble.beacon.data;

import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import com.code_embryo.android.ble.beacon.record.BeaconRecord;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconIdTest {
  ScanRecord mockRecord;
  ScanResult mockResult;
  BeaconRecord record;

  @Before
  public void setUp() {
    mockRecord = Mockito.mock(ScanRecord.class);
    mockResult = Mockito.mock(ScanResult.class);
    Mockito.when(mockResult.getScanRecord()).thenReturn(mockRecord);

    byte[] rawRecord = new byte[]{
            (byte) 0x02, (byte) 0x01, (byte) 0x1A,
            (byte) 0x1A, (byte) 0xFF, (byte) 0x4C, (byte) 0x00, // AdType, CompanyId
            (byte) 0x02, (byte) 0x15, // FormatInfo
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, // Uuid
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10,
            (byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0x55, // Major, Minor
            (byte) 0x80 // TxPower
    };
    Mockito.when(mockRecord.getBytes()).thenReturn(rawRecord);
    record = BeaconRecord.generate(mockResult);
  }

  @Test
  public void constructor() {
    BeaconId beaconId = new BeaconId(record);

    assertThat(beaconId, is(notNullValue()));
  }

  @Test
  public void testUuid() throws Exception {
    BeaconId beaconId = new BeaconId(record);

    String actual = beaconId.uuid();
    String expected = "0102030405060708090A0B0C0D0E0F10";

    assertThat(actual, is(expected));
  }

  @Test
  public void testMajor() throws Exception {
    BeaconId beaconId = new BeaconId(record);

    Integer actual = beaconId.major();
    Integer expected = 0x55AA;

    assertThat(actual, is(expected));
  }

  @Test
  public void testMinor() throws Exception {
    BeaconId beaconId = new BeaconId(record);

    Integer actual = beaconId.minor();
    Integer expected = 0xAA55;

    assertThat(actual, is(expected));
  }
}