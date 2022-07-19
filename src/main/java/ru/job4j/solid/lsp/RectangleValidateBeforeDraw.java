package ru.job4j.solid.lsp;

public class RectangleValidateBeforeDraw {
    private Rectangle rectangle;

    public RectangleValidateBeforeDraw(Rectangle rectangle) {
        validate(rectangle);
        this.rectangle = rectangle;
    }

    public boolean validate(Rectangle rectangle) {
        if (rectangle.getHeight() <= 0 || rectangle.getWidth() <= 0) {
            throw new IllegalArgumentException("Прямоугольник не может быть меньше 0.");
        } else if (rectangle.getHeight() >= 50 || rectangle.getWidth() >= 50) {
            throw new IllegalArgumentException("Прямоугольник должен иметь стороны меньше 50");
        }
        return true;
    }
}
