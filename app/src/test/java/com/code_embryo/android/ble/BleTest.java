package com.code_embryo.android.ble;

import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanResult;
import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.pm.PackageManager;

import com.code_embryo.android.ble.scan.BleScan;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class BleTest implements BleScan.Callback {
  PackageManager mockManager;
  Context mockContext;
  BluetoothLeScanner mockLeScanner;

  @Before
  public void setUp() {
    mockManager = Mockito.mock(PackageManager.class);
    mockContext = Mockito.mock(Context.class);
    mockLeScanner = Mockito.mock(BluetoothLeScanner.class);

    Mockito.when(mockManager.hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)).thenReturn(true);
}

  @Test
  public void testIsSupported() throws Exception {
    boolean actual = Ble.isSupported(mockManager);

    assertThat(actual, is(true));
  }

  @Test
  public void testStartScan() throws Exception {
    Ble ble = new Ble(mockContext, this) {
      @Override
      BluetoothLeScanner getLeScanner(Context context) {
        return mockLeScanner;
      }
    };
    ble.startScan();
  }

  @Test
  public void testStopScan() throws Exception {
    Ble ble = new Ble(mockContext, this) {
      @Override
      BluetoothLeScanner getLeScanner(Context context) {
        return mockLeScanner;
      }
    };
    ble.stopScan();
  }

  @Override
  public void onScanResult(ScanResult result) {

  }
}