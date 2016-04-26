package com.code_embryo.android.ble.beacon;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.ScanRecord;
import android.bluetooth.le.ScanResult;

import com.code_embryo.android.ble.beacon.data.DeviceAddress;
import com.code_embryo.android.ble.beacon.data.FindDate;
import com.code_embryo.android.ble.beacon.data.radio.Rssi;
import com.code_embryo.android.ble.beacon.record.BeaconRecord;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BeaconTest {
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
  public void testGenerate() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assertThat(beacon, is(notNullValue()));
  }

  @Test
  public void testDate() throws Exception {
    DeviceAddress address = new DeviceAddress("00:11:22:33:44:55");
    BeaconRecord record = BeaconRecord.generate(mockResult);
    Rssi rssi = new Rssi(-59);

    final FindDate findDate = new FindDate();
    Beacon beacon = new Beacon(address, record, rssi) {
      @Override
      FindDate generateFindDate() {
        return findDate;
      }
    };

    Long actual = beacon.date();
    Long expected = findDate.value();

    assertThat(actual, is(expected));
  }

  @Test
  public void testDeviceAddress() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    String actual = beacon.deviceAddress();
    String expected = "00:11:22:33:44:55";

    assertThat(actual, is(expected));
  }

  @Test
  public void testUuid() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    String actual = beacon.uuid();
    String expected = "0102030405060708090A0B0C0D0E0F10";

    assertThat(actual, is(expected));
  }

  @Test
  public void testMajor() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    Integer actual = beacon.major();
    Integer expected = 0x55AA;

    assertThat(actual, is(expected));
  }

  @Test
  public void testMinor() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    Integer actual = beacon.minor();
    Integer expected = 0xAA55;

    assertThat(actual, is(expected));
  }

  @Test
  public void testRssi() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    Integer actual = beacon.rssi();
    Integer expected = -59;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTxPower() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    Integer actual = beacon.txPower();
    Integer expected = -128;

    assertThat(actual, is(expected));
  }

  @Test
  public void testAccuracy() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    Double actual = beacon.accuracy();
    Integer signalDifference = beacon.txPower() - beacon.rssi();
    Double expected = Math.pow(10, signalDifference.doubleValue() / 20.0d);

    assertThat(actual, is(expected));
  }

  @Test
  public void testProximity() throws Exception {
    Beacon beacon = Beacon.generate(mockResult);

    assert beacon != null;
    String actual = beacon.proximity();
    String expected = "Immediate";

    assertThat(actual, is(expected));
  }
}