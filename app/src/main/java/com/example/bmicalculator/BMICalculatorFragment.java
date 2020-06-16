package com.example.bmicalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.bmicalculator.bmi.*;

public class BMICalculatorFragment extends Fragment implements RadioGroup.OnCheckedChangeListener{

    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
    Date date = new Date();

    TextView lblBMI;
    Button btnCalculate;
    TextView txtHeightCm;
    TextView txtWeight;
//  ImageView imgbmi;

    CalculateBMI calculateBMI;


//    RadioGroup radioGroup;
//    Button btnResult;
//    String gender;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_bmicalculator,container,false);
        btnCalculate = (Button) v.findViewById(R.id.btnCalculate);
        lblBMI = (TextView) v.findViewById(R.id.lblBMI);
        txtHeightCm = (TextView) v.findViewById(R.id.height);
        txtWeight = (TextView) v.findViewById(R.id.weight);

//        radioGroup = (RadioGroup) v.findViewById(R.id.radioGender);
//        btnResult = (Button) v.findViewById(R.id.btnCalculate);
//
//        radioGroup.setOnCheckedChangeListener(this);
//
//        btnResult.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getActivity(),gender ,Toast.LENGTH_SHORT).show();
//            }
//        });


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Double txthcm = Double.parseDouble(txtHeightCm.getText().toString());
                    Double txtweight = Double.parseDouble(txtWeight.getText().toString());

                    calculateBMI = new CalculateBMI(txthcm,txtweight);

                    // Calculating BMI
                    double bmi = calculateBMI.camlculatebmi(calculateBMI.getInputkg(), calculateBMI.getInputCm());

                    //Getting BMI Type
                    String bmitype = calculateBMI.getbmitype(bmi);

                    //Getting device Date
                    String dateTime = formatter.format(date);

                    // Adding Data to the Database
                    bmidatatable bmidatatable = new bmidatatable(getActivity());
                    bmidatatable.openDB();
                    bmidatatable.insertRecord(dateTime,Double.toString(bmi),bmitype);
                    bmidatatable.closeDB();

                    Toast.makeText(getActivity(),"Your BMI is " + bmi + " " + bmitype ,Toast.LENGTH_SHORT).show();

                    //Adding to Display Elements
                    lblBMI.setText("Your BMI is " + bmi);

                }

                catch (Exception x)
                {
                    Toast.makeText(getActivity(),"Please enter your weight, height and gender " ,Toast.LENGTH_SHORT).show();
//                  Toast.makeText(getActivity(),"Please provide positive value " + x,Toast.LENGTH_SHORT).show();
//                  Toast.makeText(getActivity(), "Please enter your gender " ,Toast.LENGTH_SHORT).show();
                }

            }
        });

        return v;
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
//        switch (i) {
//
//            case R.id.radioButtonOne:
//                gender="Female";
//                break;
//
//            case R.id.radioButtonTwo:
//                gender="Male";
//                break;
//        }

    }
}




