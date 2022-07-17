package ru.job4j.solid.lsp;

public class PerimeterRectangleValidate extends RectangleValidateBeforeDraw {
    private Rectangle rectangle;

    public PerimeterRectangleValidate(Rectangle rectangle) {
        super(rectangle);
        this.rectangle = rectangle;
    }

    /**
     * Без вызова валидации суперкласса данный метод может упасть с ошибкой, что является нарушение принципа lsp
     * @return
     */
    @Override
    public boolean validate() {
        if (rectangle.perimeter() <= 0) {
            throw new IllegalArgumentException("Периметр не может быть меньше нуля или равным нулю. Изменить длину сторон.");
        } else if (rectangle.perimeter() > 5000) {
            throw new IllegalArgumentException("Периметр не должен быть больше 5000. Измените длину сторон.");
        }
        return true;
    }

}
