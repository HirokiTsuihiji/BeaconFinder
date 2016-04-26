package com.code_embryo.android.ble.beacon.sort;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class SortOrderTest {
  @Test
  public void constructor() {
    SortOrder sortOrder = new SortOrder(0);

    assertThat(sortOrder, is(notNullValue()));
  }

  @Test
  public void testOverRange() {
    SortOrder sortOrder = new SortOrder(OrderType.values().length + 1);

    OrderType actual = sortOrder.type();
    OrderType expected = OrderType.DEVICE_ADDRESS;

    assertThat(actual, is(expected));
  }

  @Test
  public void testOrderDeviceAddress() throws Exception {
    SortOrder sortOrder = new SortOrder(0);

    int actual = sortOrder.order();
    int expected = OrderType.DEVICE_ADDRESS.ordinal();

    assertThat(actual, is(expected));
  }

  @Test
  public void testOrderDeviceAddressReverse() throws Exception {
    SortOrder sortOrder = new SortOrder(1);

    int actual = sortOrder.order();
    int expected = OrderType.DEVICE_ADDRESS_REVERSE.ordinal();

    assertThat(actual, is(expected));
  }

  @Test
  public void testOrderId() throws Exception {
    SortOrder sortOrder = new SortOrder(2);

    int actual = sortOrder.order();
    int expected = OrderType.ID.ordinal();

    assertThat(actual, is(expected));
  }

  @Test
  public void testOrderIdReverse() throws Exception {
    SortOrder sortOrder = new SortOrder(3);

    int actual = sortOrder.order();
    int expected = OrderType.ID_REVERSE.ordinal();

    assertThat(actual, is(expected));
  }

  @Test
  public void testTypeDeviceAddress() throws Exception {
    SortOrder sortOrder = new SortOrder(0);

    OrderType actual = sortOrder.type();
    OrderType expected = OrderType.DEVICE_ADDRESS;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTypeDeviceAddressReverse() throws Exception {
    SortOrder sortOrder = new SortOrder(1);

    OrderType actual = sortOrder.type();
    OrderType expected = OrderType.DEVICE_ADDRESS_REVERSE;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTypeId() throws Exception {
    SortOrder sortOrder = new SortOrder(2);

    OrderType actual = sortOrder.type();
    OrderType expected = OrderType.ID;

    assertThat(actual, is(expected));
  }

  @Test
  public void testTypeIdReverse() throws Exception {
    SortOrder sortOrder = new SortOrder(3);

    OrderType actual = sortOrder.type();
    OrderType expected = OrderType.ID_REVERSE;

    assertThat(actual, is(expected));
  }
}