package com.example.bmicalculator.bmi;


public class CalculateBMI {
    private double inputCm;
    private double inputKg;

    public CalculateBMI(double inputCm, double inputKg)
    {
        this.inputCm = inputCm;
        this.inputKg = inputKg;
    }

    public double getInputCm() {
        return inputCm;
    }

    public double getInputkg() {
        return inputKg;
    }

    public double camlculatebmi(double inputKg, double inputCm)
    {
        double result = 0;

        double txtheightcm = inputCm / 100;

        result = inputKg / (txtheightcm * txtheightcm);

        result = (double) Math.round(result * 100) / 100;

        return result;

    }

    public String getbmitype(double bmi)
    {
        String type = "null";

        if (bmi < 15)
        {
            type = "Very severely underweight";
        }
        else if (bmi >= 15 && bmi <= 16)
        {
            type = "Severely underweight";
        }
        else if (bmi > 16 && bmi<=18.5)
        {
            type = "Underweight";
        }
        else if (bmi > 18.5 && bmi <= 25)
        {
            type = "Normal";
        }
        else if (bmi > 25 && bmi <= 30)
        {
            type = "Overweight";
        }
        else if (bmi > 30 && bmi <= 35)
        {
            type = "Moderately obese";
        }
        else if (bmi > 35 && bmi <= 40)
        {
            type = "Severely obese";
        }
        else
        {
            type = "Extremely Obesity";
        }
        return type;
    }
}
