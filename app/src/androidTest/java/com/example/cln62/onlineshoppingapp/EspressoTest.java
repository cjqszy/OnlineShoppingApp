package com.example.cln62.onlineshoppingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;

import com.example.cln62.onlineshoppingapp.adapter.CartAdapter;
import com.example.cln62.onlineshoppingapp.ui.checkout.CartActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
public class EspressoTest {
    @Rule
    public ActivityTestRule<CartActivity> mActivityTestRule = new ActivityTestRule<>(CartActivity.class);

    @Test
    public void checkCouponAdding() {

        TextView tvTotal = mActivityTestRule.getActivity().findViewById(R.id.tv_total);
        String originalTotal = tvTotal.getText().toString().substring(1);

        String finalTotal = "$" + Double.parseDouble(originalTotal) * 0.90;

        onView(withId(R.id.tv_coupon)).perform(click());

        onView(withId(R.id.tv_total))
                .check(matches(withText(finalTotal)));
    }

    @Test
    public void checkCartItemUpdate() {

        TextView tvQuantity = mActivityTestRule.getActivity().findViewById(R.id.textview_quantity);
        String originalQuantity = tvQuantity.getText().toString();

        String finalTotal = String.valueOf(Integer.parseInt(originalQuantity) + 1);

        onView(withId(R.id.tv_add)).perform(click());

        onView(withId(R.id.textview_quantity))
                .check(matches(withText(finalTotal)));
    }


/*    @Test
    public void checkNextQuestion() {

        String questionToBeShowed = "Which countries are Asian countries ?";

        onView(withId(R.id.nextButton)).perform(click());

        onView(withId(R.id.questionTextView))
                .check(matches(withText(questionToBeShowed)));
    }


    @Test
    public void checkPrevQuestion() {

        String questionToBeShowed = "Which countries are European countries ?";

        onView(withId(R.id.nextButton)).perform(click());

        onView(withId(R.id.prevButton)).perform(click());

        onView(withId(R.id.questionTextView))
                .check(matches(withText(questionToBeShowed)));
    }*/
}
