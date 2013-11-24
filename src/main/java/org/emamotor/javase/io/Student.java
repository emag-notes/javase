package org.emamotor.javase.io;

import java.io.Serializable;

/**
 * @author Yoshimasa Tanabe
 */
public class Student implements Serializable {

    private String id;
    private transient String name;
    private char cource;
    private int level;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getCource() {
        return cource;
    }

    public void setCource(char cource) {
        this.cource = cource;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", cource=" + cource +
                ", level=" + level +
                '}';
    }
}
