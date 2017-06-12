package com.spotippos.model;

public class Boundaries {

    private Point upperLeft;
    private Point bottomRight;

    public Boundaries() {
    }

    public Boundaries(Point upperLeft, Point bottomRight) {
        this.upperLeft = upperLeft;
        this.bottomRight = bottomRight;
    }

    public Point getUpperLeft() {
        return upperLeft;
    }

    public void setUpperLeft(Point upperLeft) {
        this.upperLeft = upperLeft;
    }

    public Point getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    @Override
    public String toString() {
        return String.format("Superior esquerdo (X: %d Y: %d), Inferior direito (X: %d Y: %d)", upperLeft.getX(), upperLeft.getY(), bottomRight.getX(), bottomRight.getY());
    }

}
