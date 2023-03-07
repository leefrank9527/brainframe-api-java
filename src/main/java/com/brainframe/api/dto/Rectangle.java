package com.brainframe.api.dto;

public class Rectangle {
    private Coord leftTop;
    private Coord rightTop;

    private Coord rightBottom;

    private Coord leftBottom;

    public Coord getLeftTop() {
        return leftTop;
    }

    public void setLeftTop(Coord leftTop) {
        this.leftTop = leftTop;
    }

    public Coord getRightTop() {
        return rightTop;
    }

    public void setRightTop(Coord rightTop) {
        this.rightTop = rightTop;
    }

    public Coord getRightBottom() {
        return rightBottom;
    }

    public void setRightBottom(Coord rightBottom) {
        this.rightBottom = rightBottom;
    }

    public Coord getLeftBottom() {
        return leftBottom;
    }

    public void setLeftBottom(Coord leftBottom) {
        this.leftBottom = leftBottom;
    }
}
