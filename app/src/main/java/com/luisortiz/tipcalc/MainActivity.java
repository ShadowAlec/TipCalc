package com.luisortiz.tipcalc;

import android.content.Context;
import android.content.Intent;
import android.hardware.input.InputManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.btnSubmit)
    Button btnSubmit;
    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.btnIncrease)
    Button btnIncrease;
    @Bind(R.id.btnDecrease)
    Button btnDecrease;
    @Bind(R.id.btnClear)
    Button btnClear;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_CHANGE = 10;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        inputPercentage.setText(String.valueOf(DEFAULT_TIP_CHANGE));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.btnSubmit)
    public void handleSubmit()
    {
        hideKeyboard();
        String strInputTotal = inputBill.getText().toString().trim();
        if(!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();
            double tip = total*tipPercentage/100d;
            String strTip = String.format(getString(R.string.global_message_bill),tip);
            txtTip.setText(strTip);
            txtTip.setVisibility(View.VISIBLE);

        }
    }


    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease()
    {
        // LLamar a handleTipChange y sumar 1
        handleTipChange(1);
        return;
    }
    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease()
    {
        // LLamar a handleTipChange y restar 1
        handleTipChange(-1);
        return;
    }


    public int getTipPercentage() {
        // 1 Crear una variable tipPercentage en la que guardemos DEFAULT_TIP_CHANGE
        int tipPercentage = DEFAULT_TIP_CHANGE;
        // 2 Crear una variable String strInputTipPercentage que tome el valor del inputPercentage (No olvidar el trim)
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        // 3 Verificar que la cadena no venga vacía.
        if (!strInputTipPercentage.isEmpty())
        {
            //      3.1 Si no viene vacía, Sobreescribir tipPercentage con el valor de strInputTipPercentage. (Convertir a entero)
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }
        else {
            //      3.2 Si es vacía: inputPercentage.setText(String.valueOf(Default_TIP_PERCENTAGE));
            inputPercentage.setText(String.valueOf(DEFAULT_TIP_CHANGE));
        }
        // 4 Devolver el valor de TipPercentage.
        return tipPercentage;

    }

    public void handleTipChange(int change)
    {
        // 1 Llamar a GetTipPercentage (guardar en una variable)
        int newTip = getTipPercentage();
        // 2 Aplicar el incremento, decremento, que viene en la variable change
        newTip = newTip + change;
        // 3 Si TipPercentage mayor a cero, colocar el valor en la input.
        if(newTip>0)
        {
            inputPercentage.setText(String.valueOf(newTip));
        }

    }

    public void hideKeyboard()
    {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try
        {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),    InputMethodManager.HIDE_IMPLICIT_ONLY);
        } catch(NullPointerException npe) {
            Log.e(getLocalClassName(),Log.getStackTraceString(npe));
        }
    }



    private void about() {
        Log.v("ABOUT", "Hola, este es el boton de about");

        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}

