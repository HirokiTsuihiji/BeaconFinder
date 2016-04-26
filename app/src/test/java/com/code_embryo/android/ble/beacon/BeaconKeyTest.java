package com.code_embryo.android.ble.beacon;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconKeyTest {
  ScanRecord mockRecord;
  ScanResult mockResult;
  BluetoothDevice mockBluetooth;

  @Before
  public void setUp() {
    mockRecord = Mockito.mock(ScanRecord.class);
    mockResult = Mockito.mock(ScanResult.class);
    mockBluetooth = Mockito.mock(BluetoothDevice.class);

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

    Mockito.when(mockResult.getDevice()).thenReturn(mockBluetooth);
    Mockito.when(mockBluetooth.getAddress()).thenReturn("00:11:22:33:44:55");
  }

  @Test
  public void constructor() {
    Beacon beacon = Beacon.generate(mockResult);
    BeaconKey key = new BeaconKey(beacon);

    assertThat(key, is(notNullValue()));
  }

  @Test
  public void testValue() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);
    BeaconKey key = new BeaconKey(beacon);

    String actual = key.value();
    String expected = "0102030405060708090A0B0C0D0E0F10"
            + String.valueOf(0x000055AA)
            + String.valueOf(0x0000AA55);

    assertThat(actual, is(expected));
  }
}