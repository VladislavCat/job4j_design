package ru.job4j.solid.lsp;

public class Square implements Figure {
    private int width;
    private int height;

    public Square(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int perimeter() {
        return height * width;
    }

    public String drawSquare() {
        return null;
    }
}
