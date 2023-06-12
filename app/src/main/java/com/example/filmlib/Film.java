package com.example.filmlib;

public class Film {

    String Name;
    String Status;
    String Rating;
    String Description;

    public Film(String Name,String Status,String Rating,String Description){
        this.Name = Name;
        this.Status = Status;
        this.Rating = Rating;
        this.Description = Description;
    }

    public String getName() {
        return Name;
    }

    public String getStatus() {
        return Status;
    }

    public String getRating() {
        return Rating;
    }

    public String getDescription() {
        return Description;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setDescription(String description) {
        Description = description;
    }
}
