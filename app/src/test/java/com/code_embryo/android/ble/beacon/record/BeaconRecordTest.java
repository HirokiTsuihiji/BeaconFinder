package com.code_embryo.android.ble.beacon.record;

import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import com.code_embryo.android.ble.beacon.Beacon;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconRecordTest {
  ScanRecord mockRecord;
  ScanResult mockResult;

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
  }

  @Test
  public void testGenerate() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    assertThat(record, is(notNullValue()));
  }

  @Test
  public void testInputNullValue() {
    BeaconRecord record = BeaconRecord.generate(null);

    assertThat(record, is(nullValue()));
  }

  @Test
  public void testIncorrectValidate() {
    byte[] rawRecord = new byte[]{
            (byte) 0x02, (byte) 0x01, (byte) 0x1A,
            (byte) 0x1A, (byte) 0xFF, (byte) 0x4C, (byte) 0x01, // AdType, CompanyId (incorrect)
            (byte) 0x02, (byte) 0x15, // FormatInfo
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, // Uuid
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10,
            (byte) 0x55, (byte) 0xAA, (byte) 0xAA, (byte) 0x55, // Major, Minor
            (byte) 0x80 // TxPower
    };
    Mockito.when(mockRecord.getBytes()).thenReturn(rawRecord);

    BeaconRecord record = BeaconRecord.generate(mockResult);

    assertThat(record, is(nullValue()));
  }

  @Test
  public void testAdType() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte actual = record.adType();
    byte expected = (byte) 0xFF;

    assertThat(actual, is(expected));
  }

  @Test
  public void testCompanyId() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte[] actual = record.companyId();
    byte[] expected = new byte[]{(byte) 0x4C, (byte) 0x00};

    assertThat(actual, is(expected));
  }

  @Test
  public void testBeaconFormatInfo() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte[] actual = record.beaconFormatInfo();
    byte[] expected = new byte[]{(byte) 0x02, (byte) 0x15};

    assertThat(actual, is(expected));
  }

  @Test
  public void testUuid() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte[] actual = record.uuid();
    byte[] expected = new byte[] {
            (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04, // Uuid
            (byte) 0x05, (byte) 0x06, (byte) 0x07, (byte) 0x08,
            (byte) 0x09, (byte) 0x0A, (byte) 0x0B, (byte) 0x0C,
            (byte) 0x0D, (byte) 0x0E, (byte) 0x0F, (byte) 0x10
    };

    assertThat(actual, is(expected));
  }

  @Test
  public void testMajor() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte[] actual = record.major();
    byte[] expected = new byte[] {(byte) 0x55, (byte) 0xAA};

    assertThat(actual, is(expected));
  }

  @Test
  public void testMinor() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte[] actual = record.minor();
    byte[] expected = new byte[]{(byte) 0xAA, (byte) 0x55};

    assertThat(actual, is(expected));
  }

  @Test
  public void testTxPower() throws Exception {
    BeaconRecord record = BeaconRecord.generate(mockResult);

    byte actual = record.txPower();
    byte expected = -128;

    assertThat(actual, is(expected));
  }
}