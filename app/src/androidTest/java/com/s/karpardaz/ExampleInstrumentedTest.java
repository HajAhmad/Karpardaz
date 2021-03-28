package com.s.karpardaz;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import dagger.hilt.android.testing.HiltAndroidRule;
import dagger.hilt.android.testing.HiltAndroidTest;

@HiltAndroidTest
public class ExampleInstrumentedTest {

    @Rule
    HiltAndroidRule hiltRule = new HiltAndroidRule(this);

    @Before
    public void setup() {
        hiltRule.inject();
    }

    @Test
    public void checkPresenter() {

    }
}
