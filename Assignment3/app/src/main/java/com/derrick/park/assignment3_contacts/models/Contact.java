package com.derrick.park.assignment3_contacts.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contact implements Comparable<Contact>{
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("name")
    @Expose
    private Name name;
    @SerializedName("location")
    @Expose
    private Location location;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("cell")
    @Expose
    private String cell;

    private boolean header = false;

    public String getGender() {
        return gender;
    }

    public Name getName() {
        return name;
    }

    public Location getLocation() {
        return location;
    }

    public String getEmail() {
        return email;
    }

    public String getCell() {
        return cell;
    }

    public void setName(String first,String last){
        Name n1 = new Name();
        n1.setFirst(first);
        n1.setLast(last);
        this.name = n1;

    }

    public void setCell(String phone){
        this.cell = phone;
    }

    public boolean isHeader(){
        return header;
    }

    public void setHeaderTrue(){
        header = true;
    }

    public void setHeaderFalse(){
        header = false;
    }

    @Override
    public String toString() {
        return String.format("%n%s%n%s%n%s%n%s%n%s", name, location, email, cell, Boolean.toString(header));
    }

    @Override
    public int compareTo(Contact o) {
        return name.toString().toLowerCase().compareTo(o.name.toString().toLowerCase());
    }

    /**
     * Name {first: , last: }
     */
   public static class Name {
        @SerializedName("first")
        @Expose
        private String first;
        @SerializedName("last")
        @Expose
        private String last;

        public String getFirst() {
            return first;
        }

        public String getLast() {
            return last;
        }

        public void setFirst(String first){
            this.first = first;
        }

        public void setLast(String last){
            this.last = last;
        }

        @Override
        public String toString() {
            return first + " " + last;
        }
    }

    /**
     * Location {street: , city: , state: , postcode: }
     */
    class Location {
        @SerializedName("street")
        @Expose
        private String street;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state")
        @Expose
        private String province;
        @SerializedName("postcode")
        @Expose
        private String postcode;

        public String getStreet() {
            return street;
        }

        public String getCity() {
            return city;
        }

        public String getProvince() {
            return province;
        }

        public String getPostcode() {
            return postcode;
        }

        @Override
        public String toString() {
            return street + ", " + city + ", " + province + " Canada " + postcode;
        }
    }
}

