package ru.job4j.solid.isp.menu;


import static ru.job4j.solid.isp.menu.ConsoleMenuUI.STUB_ACTION;

public class AddTuskAction implements Action {
    private final Input input;
    private final Menu menu;

    public AddTuskAction(Input input, Menu menu) {
        this.input = input;
        this.menu = menu;
    }

    @Override
    public void action() {
        String nameParentTusk = input.askStr("Введите имя родительской задачи, если оно есть."
                + " Если задача первичная, поставьте \"-\"");
        String nameTusk = input.askStr("Введите имя задачи.");
        if (nameParentTusk.equals("-")) {
           menu.add(Menu.ROOT, nameTusk, STUB_ACTION);
        } else {
           menu.add(nameParentTusk, nameTusk, STUB_ACTION);
        }
    }
}
