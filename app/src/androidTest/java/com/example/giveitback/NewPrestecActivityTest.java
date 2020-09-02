package com.example.giveitback;

import android.widget.Spinner;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.MediumTest;
import androidx.test.rule.ActivityTestRule;

import junit.framework.TestCase;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class NewPrestecActivityTest extends TestCase {

    @Rule
    public ActivityTestRule<NewPrestecActivity> rule  = new  ActivityTestRule<>(NewPrestecActivity.class);

    @Test
    public void onCreate() {
        NewPrestecActivity activity = rule.getActivity();

        Spinner persones = activity.findViewById(R.id.spinnerPersones);
        assertThat(persones.getCount(), greaterThanOrEqualTo(1));

        Spinner objectes = activity.findViewById(R.id.spinnerObjectes);
        assertThat(objectes.getCount(), greaterThanOrEqualTo(1));

    }
}