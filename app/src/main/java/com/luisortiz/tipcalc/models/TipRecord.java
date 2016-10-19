package com.luisortiz.tipcalc.models;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Created by luis on 16/10/16.
 */

public class TipRecord {

    private double bill;
    private int tipPercentage;
    private Date timestamp;

    public double getBill(){
        return bill;
    }

    public void setBill(double bill)
    {
        this.bill = bill;
    }

    public double getTipPercentage()
    {
        return tipPercentage;
    }

    public void setTipPercentage(int tipPercentage){
        this.tipPercentage = tipPercentage;
    }

    public  Date getTimestamp()
    {
        return timestamp;
    }

    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }

    public  double getTip(){
        return bill*(tipPercentage/100d);
    }

    public String getDateFormated(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM dd, yyyy HH::mm");
        return simpleDateFormat.format(timestamp);
    }

}
