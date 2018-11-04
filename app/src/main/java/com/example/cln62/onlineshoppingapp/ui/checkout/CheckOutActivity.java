package com.example.cln62.onlineshoppingapp.ui.checkout;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cln62.onlineshoppingapp.R;
import com.paypal.android.sdk.payments.PayPalConfiguration;
import com.paypal.android.sdk.payments.PayPalPayment;
import com.paypal.android.sdk.payments.PayPalService;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.json.JSONException;

import java.math.BigDecimal;

public class CheckOutActivity extends AppCompatActivity {

    int testFlag = 1;
    private static final String CONFIG_ENVIRONMENT = PayPalConfiguration.ENVIRONMENT_NO_NETWORK;
    // note that these credentials will differ between live & sandbox environments.
    private static final String CONFIG_CLIENT_ID = "credentials from developer.paypal.com";
    private static final int REQUEST_CODE_PAYMENT = 1;

    EditText editTextBAddress, editTextDAddress;
    Button buttonOrder;
    private static final String TAG = "CheckOutActivity";

    double payment;

  /*  TextView textViewOrderId, textViewOrderStatus, textViewUserName, textViewBillingAddress, textViewDelieveryAddress;
    TextView textViewMobile, textViewOrderEmail, textViewItemId, textViewItemName, textViewItemQuantity;
    TextView textViewTotalPrice, textViewPaidPrice, textViewDate;*/

    TextView textViewOrderSummary;

    private static PayPalConfiguration config = new PayPalConfiguration()
            .environment(CONFIG_ENVIRONMENT)
            .clientId(CONFIG_CLIENT_ID)
            // The following are only used in PayPalFuturePaymentActivity.
            .merchantName("Example Merchant")
            .merchantPrivacyPolicyUri(Uri.parse("https://www.example.com/privacy"))
            .merchantUserAgreementUri(Uri.parse("https://www.example.com/legal"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);
        buttonOrder = findViewById(R.id.button_order);
        editTextBAddress = findViewById(R.id.edittext_billingadd);
        editTextDAddress = findViewById(R.id.edittext_deliveryadd);
//        textViewOrderSummary = findViewById(R.id.textview_ordersummary);

        payment = getIntent().getDoubleExtra("payment", 0);
        Intent intent = new Intent(this, PayPalService.class);
        intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
        startService(intent);

/*        if (testFlag == 0) {
            getSupportFragmentManager().beginTransaction().add(R.id.checkoutact_layout, new EmptyCartFragment(), null ).commit();
        }*/

        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PayPalPayment thingToBuy = getThingToBuy(PayPalPayment.PAYMENT_INTENT_SALE);
                Intent intent = new Intent(CheckOutActivity.this, PaymentActivity.class);

                intent.putExtra(PayPalService.EXTRA_PAYPAL_CONFIGURATION, config);
                intent.putExtra(PaymentActivity.EXTRA_PAYMENT, thingToBuy);
                startActivityForResult(intent, REQUEST_CODE_PAYMENT);
            }
        });
    }

    private PayPalPayment getThingToBuy(String paymentIntentSale) {
        return new PayPalPayment(new BigDecimal(payment), "USD", "tshirt", paymentIntentSale);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        Log.i(TAG, confirm.toJSONObject().toString(4));
                        Log.i(TAG, confirm.getPayment().toJSONObject().toString(4));
                        displayResultText("PaymentConfirmation info received from PayPal");
                        orderSummary();
                        Log.i(TAG, "1");

                    } catch (JSONException e) {
                        Log.e(TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(TAG, "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        TAG,
                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }
        }
    }

    private void displayResultText (String paymentConfirmation_info_received_from_payPal){

        Toast.makeText(this, "" + paymentConfirmation_info_received_from_payPal, Toast.LENGTH_SHORT).show();
    }

    private void orderSummary() {

        String bAddress = editTextBAddress.getText().toString();
        String dAddress = editTextDAddress.getText().toString();
        Bundle bundle = new Bundle();
        bundle.putString("bAddress", bAddress);
        bundle.putString("dAddress", dAddress);
        bundle.putString("payment", String.valueOf(payment));
        CheckoutFragment checkoutFragment = new CheckoutFragment();
        checkoutFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.content_checkout, checkoutFragment, null).commit();
//        getSupportFragmentManager().beginTransaction().replace(R.id.content_checkout, new TestFragment(), null).commit();
//        buttonOrder.setVisibility(View.GONE);

    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(this, PayPalService.class));
        super.onDestroy();
    }

    public void hideEditText() {

        editTextBAddress.setVisibility(View.GONE);
        editTextDAddress.setVisibility(View.GONE);
        buttonOrder.setText("back to home page");
        buttonOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CheckOutActivity.this, "222", Toast.LENGTH_SHORT).show();
            }
        });
    }

/*    public void showOrderSummary(String orderSummary) {

        textViewOrderSummary.setText(orderSummary);
    }*/
}
