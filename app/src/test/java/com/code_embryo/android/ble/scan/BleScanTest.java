package com.code_embryo.android.ble.scan;

import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BleScanTest implements BleScan.Callback {
  ScanRecord mockRecord;
  ScanResult mockResult;
  ScanResult scanResult;

  @Before
  public void setUp() {
    mockRecord = Mockito.mock(ScanRecord.class);
    mockResult = Mockito.mock(ScanResult.class);

    Mockito.when(mockResult.getScanRecord()).thenReturn(mockRecord);
    Mockito.when(mockResult.getRssi()).thenReturn(-59);

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
  public void constructor() throws Exception {
    BleScan bleScan = new BleScan(this);

    assertThat(bleScan, is(notNullValue()));
  }

  @Test
  public void testGetCallback() {
    BleScan bleScan = new BleScan(this);

    ScanCallback callback = bleScan.getCallback();
    callback.onScanResult(0, mockResult);

    assertThat(mockResult, is(scanResult));
  }

  @Override
  public void onScanResult(ScanResult result) {
    scanResult = result;
  }
}