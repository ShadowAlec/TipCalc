package com.luisortiz.tipcalc.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.luisortiz.tipcalc.fragments.TipHistoryListFragment;
import com.luisortiz.tipcalc.R;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AdvancedTipActivity extends AppCompatActivity {

    @Bind(R.id.advancedBillTextView)
    TextView advancedBillTextView;
    @Bind(R.id.advancedTipTextView)
    TextView advancedTipTextView;
    @Bind(R.id.advancedDateTextView)
    TextView advancedDateTextView;
    @Bind(R.id.activity_advanced_tip)
    RelativeLayout activityAdvancedTip;
    private String tipText;
    private String billText;
    private String dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced_tip);
        ButterKnife.bind(this);

        Intent intent = getIntent();


        billText = getString(R.string.tipdetail_message_bill).concat(intent.getStringExtra(TipHistoryListFragment.BILL_MESSAGE));
        tipText = getString(R.string.tipdetail_message_tip).concat(intent.getStringExtra(TipHistoryListFragment.TIP_MESSAGE));
        dateText = getString(R.string.tipdetail_message_date).concat(intent.getStringExtra(TipHistoryListFragment.DATE_MESSAGE));

        advancedBillTextView.setText(billText);
        advancedTipTextView.setText(tipText);
        advancedDateTextView.setText(dateText);

    }
}
