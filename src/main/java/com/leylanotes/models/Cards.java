package com.leylanotes.models;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Entity
public class Cards {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private Long id;

    private String frontSide;

    private String backSide;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFrontSide() {
        return frontSide;
    }

    public void setFrontSide(String frontSide) {
        this.frontSide = frontSide;
    }

    public String getBackSide() {
        return backSide;
    }

    public void setBackSide(String backSide) {
        this.backSide = backSide;
    }

    public Cards(String frontSide, String backSide) {
        this.frontSide = frontSide;
        this.backSide = backSide;
    }

    public Cards() {
    }
}
