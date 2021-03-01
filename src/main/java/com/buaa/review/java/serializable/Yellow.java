package com.buaa.review.java.serializable;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;

public class Yellow implements Serializable {
    private int id;
    private String ch;

    public Yellow(int id, String ch){
        this.id = id;
        this.ch = ch;
    }

    @Override
    public String toString() {
        return "Yellow{" +
                "id=" + id +
                ", ch='" + ch + '\'' +
                '}';
    }
}

class main{
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Yellow hs = new Yellow(1, "hs");
//        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.txt"));
//        oos.writeObject(hs);
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.txt"));
        Object o = ois.readObject();
        System.out.println(o);
    }
}