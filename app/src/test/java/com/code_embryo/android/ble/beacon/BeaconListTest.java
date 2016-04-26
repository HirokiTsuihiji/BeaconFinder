package com.code_embryo.android.ble.beacon;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconListTest {
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
  public void testRegisterAndGet() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);
    BeaconKey key = new BeaconKey(beacon);
    BeaconList beaconList = new BeaconList();

    beaconList.register(key, beacon);

    Beacon actual = beaconList.beacon(key);
    Beacon expected = beacon;

    assertThat(actual, is(expected));
  }

  @Test
  public void testRemove() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);
    BeaconKey key = new BeaconKey(beacon);
    BeaconList beaconList = new BeaconList();
    beaconList.register(key, beacon);

    beaconList.remove(key);

    Beacon actual = beaconList.beacon(key);
    Beacon expected = null;

    assertThat(actual, is(expected));

  }

  @Test
  public void testArray() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);
    BeaconKey key = new BeaconKey(beacon);
    BeaconList beaconList = new BeaconList();
    beaconList.register(key, beacon);

    ArrayList<Beacon> list = beaconList.array();

    Beacon actual = list.get(0);
    Beacon expected = beacon;

    assertThat(actual, is(expected));
  }
}