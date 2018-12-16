package com.example.norskhaxor.coffeeshop.models;

public class Images {
    private Standard_resolution standard_resolustion;

    public Standard_resolution getStandard_resolustion(){
        return standard_resolustion;
    }

    public class Standard_resolution{
        private String url;
        public String getUrl(){
            return url;
        }

    }
}
