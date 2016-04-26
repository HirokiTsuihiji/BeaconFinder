package com.code_embryo.android.ble.scan.failed;

import android.bluetooth.le.ScanCallback;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class ScanFailedTest {

  @Test
  public void testMessageAlreadyStarted() throws Exception {
    ScanFailed scanFailed = new ScanFailed();
    ScanFailedMessage message = scanFailed.message(ScanCallback.SCAN_FAILED_ALREADY_STARTED);

    String actual = message.value();
    String expected = "Scanning has already been started.";

    assertThat(actual, is(expected));
  }

  @Test
  public void testMessageFeatureUnsupported() throws Exception {
    ScanFailed scanFailed = new ScanFailed();
    ScanFailedMessage message = scanFailed.message(ScanCallback.SCAN_FAILED_FEATURE_UNSUPPORTED);

    String actual = message.value();
    String expected = "Unsupported feature.";

    assertThat(actual, is(expected));
  }

  @Test
  public void testMessageInternalError() throws Exception {
    ScanFailed scanFailed = new ScanFailed();
    ScanFailedMessage message = scanFailed.message(ScanCallback.SCAN_FAILED_INTERNAL_ERROR);

    String actual = message.value();
    String expected = "An internal error occurred.";

    assertThat(actual, is(expected));
  }

  @Test
  public void testMessageRegistrationFailed() throws Exception {
    ScanFailed scanFailed = new ScanFailed();
    ScanFailedMessage message = scanFailed.message(ScanCallback.SCAN_FAILED_APPLICATION_REGISTRATION_FAILED);

    String actual = message.value();
    String expected = "Registration failed.";

    assertThat(actual, is(expected));
  }
}