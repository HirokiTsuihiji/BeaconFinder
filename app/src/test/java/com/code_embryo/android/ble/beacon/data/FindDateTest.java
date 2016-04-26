package com.code_embryo.android.ble.beacon.data;

import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class FindDateTest {

  @Test
  public void constructor() {
    FindDate date = new FindDate();

    assertThat(date, is(notNullValue()));
  }

  @Test
  public void testValue() throws Exception {
    final Date current = new Date();
    FindDate findDate = new FindDate() {
      @Override
      Date getNowDate() {
        return current;
      }
    };

    Long actual = findDate.value();
    Long expected = current.getTime();

    assertThat(actual, is(expected));
  }
}