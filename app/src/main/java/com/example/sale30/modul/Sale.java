package com.example.sale30.modul;

import com.example.sale30.R;

public class Sale {
    private String Name;
    private String Card;
    private int ImageId;
    private String Direction;
    private Sale(String name, String card, int imageId, String direction){
    this.Name = name;
    this.Card = card;
    this.ImageId = imageId;
    this.Direction = direction;
}
public static final Sale[] sale = {new Sale("Nam", "200.000", R.drawable.hinh1, "Cao 175 cm, nặng 60kg")};

    public String getName() {
        return Name;
    }

    public String getCard() {
        return Card;
    }

    public int getImageId() {
        return ImageId;
    }

    public String getDirection() {
        return Direction;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "Name='" + Name + '\'' +
                ", Card='" + Card + '\'' +
                ", ImageId=" + ImageId +
                ", Direction='" + Direction + '\'' +
                '}';
    }
}