package com.example.p440;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private Button addButton;
    private Spinner spinner;
    private ListView listView;
    private int spinnerIndex;
    private ArrayList<Integer> spinnerMenu;
    private CustomerAdapter customerAdapter;
    private LinearLayout linearLayout;

    private class CustomerAdapter extends BaseAdapter {
        ArrayList<Customer> customers;

        public CustomerAdapter() {
            customers = new ArrayList<Customer>();
        }

        @Override
        public int getCount() {
            return customers.size();
        }

        @Override
        public Object getItem(int i) {
            return customers.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View customersView;

            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            customersView = inflater.inflate(R.layout.customerlist_layout, linearLayout,true);

            TextView customerName = customersView.findViewById(R.id.customerName);
            TextView customerPhone = customersView.findViewById(R.id.customerPhone);
            ImageView customerImg = customersView.findViewById(R.id.customerImg);

            customerImg.setImageResource(customers.get(i).getImgid());
            customerName.setText(customers.get(i).getName());
            customerPhone.setText(customers.get(i).getPhone());

            return customersView;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mappingValues();

        makeSpinner();
        makeSpinnerEvent();
        makeListView();
        makeButtonEvent();
    }

    private void makeSpinnerEvent() {
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerIndex = spinnerMenu.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        };

        spinner.setOnItemSelectedListener(spinnerListener);
    }

    private void makeButtonEvent() {
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText nameText = findViewById(R.id.nameText);
                EditText phoneText = findViewById(R.id.phoneText);

                String name = nameText.getText().toString();
                String phone = phoneText.getText().toString();

                if (!name.equals("") && !phone.equals("")) {
                    Customer customer = new Customer(name, phone, spinnerIndex);

                    customerAdapter.customers.add(customer);
                    customerAdapter.notifyDataSetChanged();

                    TextView customerCountText = findViewById(R.id.customerCountText);
                    customerCountText.setText(customerAdapter.customers.size() + " 명");
                }
            }
        });
    }

    private void makeListView() {
        customerAdapter = new CustomerAdapter();
        listView.setAdapter(customerAdapter);
    }

    private void mappingValues() {
        spinner = findViewById(R.id.spinner);
        listView = findViewById(R.id.listView);
        linearLayout = findViewById(R.id.container);
        addButton = findViewById(R.id.addButton);
    }

    private void makeSpinner() {
        spinnerMenu = new ArrayList<>();
        spinnerMenu.add(R.drawable.ic_launcher_foreground);
        spinnerMenu.add(R.drawable.ic_launcher_background);

        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, R.layout.support_simple_spinner_dropdown_item, spinnerMenu);
        spinner.setAdapter(arrayAdapter);
    }
}
