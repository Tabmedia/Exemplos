package br.com.tabmedia.integration;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText crm;
    private EditText crmUf;
    private EditText doctor;
    private EditText products;
    private EditText speciality;
    private EditText visit;
    private EditText categories;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        crm = (EditText) findViewById(R.id.crm);
        crmUf = (EditText) findViewById(R.id.crmuf);
        doctor = (EditText) findViewById(R.id.doctor);
        products = (EditText) findViewById(R.id.products);
        speciality = (EditText) findViewById(R.id.speciality);
        visit = (EditText) findViewById(R.id.visit);
        categories = (EditText) findViewById(R.id.products_categories);

        Button btOpenTabmedia = (Button) findViewById(R.id.bt_open_tabmedia);
        btOpenTabmedia.setOnClickListener(this);
    }

    public void onClick(View v) {

        /*Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        intent.setAction("br.com.tabmedia.openTabmedia");

        intent.putExtra("name", getValue(doctor));
        intent.putExtra("crm", getValue(crm));
        intent.putExtra("crmUF", getValue(crmUf));
        intent.putExtra("specialityName", getValue(speciality));
        intent.putExtra("products", getValue(products));
        intent.putExtra("presentationNumber", Integer.valueOf(getValue(visit)));

        sendBroadcast(intent);*/

        Uri uri = Uri.parse("tabmedia://");

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        intent.putExtra("name", getValue(doctor));
        intent.putExtra("crm", getValue(crm));
        intent.putExtra("crmUF", getValue(crmUf));
        intent.putExtra("specialityName", getValue(speciality));
        intent.putExtra("products", getValue(products));
        intent.putExtra("visit", Integer.valueOf(getValue(visit)));

        String categoriesText = getValue(categories);

        if(categoriesText != null){
            String[] categoriesArray = categoriesText.split(",");
            intent.putExtra("productsCategories", categoriesArray);
        }

        startActivity(intent);
    }

    private String getValue(EditText editText) {
        if (editText.equals(visit)) {
            return (editText.getText().length() > 0) ? editText.getText().toString() : "1";
        } else {
            return (editText.getText().length() > 0) ? editText.getText().toString() : null;
        }
    }
}
