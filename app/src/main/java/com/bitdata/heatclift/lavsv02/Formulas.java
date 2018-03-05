package com.bitdata.heatclift.lavsv02;

/**
 * Created by Heatclift on 05/03/2018.
 */

public class Formulas {
    public double calcPayment(double presentValue, int financingPeriod, double interestRatePerYear)
    {
        double principal = presentValue;
        double interest = interestRatePerYear * .01;
        double payments = month_to_days(financingPeriod);

        // Now compute the monthly payment figure, using esoteric math.
        double gross = ((principal * interest) * financingPeriod) + presentValue;
        double daily = gross / payments;

        // Check that the result is a finite number. If so, display the results.

        return daily;



    }
    public double calcinterest(double presentValue, int financingPeriod, double interestRatePerYear)
    {
        double principal = presentValue;
        double interest = interestRatePerYear * .01;
        double payments = month_to_days(financingPeriod);

        // Now compute the monthly payment figure, using esoteric math.
        double gross = (principal * interest) * financingPeriod;
        double daily = gross / payments;

        // Check that the result is a finite number. If so, display the results.

        return daily;



    }
    public double gross(double presentValue, int financingPeriod, double interestRatePerYear)
    {
        double principal = presentValue;
        double interest = interestRatePerYear * .01;
        double payments = month_to_days(financingPeriod);

        // Now compute the monthly payment figure, using esoteric math.
        double gross = (principal * interest) * financingPeriod;


        // Check that the result is a finite number. If so, display the results.

        return gross + principal;



    }
    public double interest(double presentValue, int financingPeriod, double interestRatePerYear)
    {
        double principal = presentValue;
        double interest = interestRatePerYear * .01;
        double payments = month_to_days(financingPeriod);

        // Now compute the monthly payment figure, using esoteric math.
        double gross = (principal * interest);
        double inter = gross * financingPeriod;

        // Check that the result is a finite number. If so, display the results.

        return inter;



    }
    public int month_to_days(int months)
    {
        return months * 31;
    }

}
