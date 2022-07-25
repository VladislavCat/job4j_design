package ru.job4j.solid.isp.menu;

import java.util.Optional;

public class SelectForNameAction implements Action {
    private final Input input;
    private final Menu menu;

    public SelectForNameAction(Input input, Menu menu) {
        this.input = input;
        this.menu = menu;
    }
    @Override
    public void action() {
        String name = input.askStr("Введите имя задачи.");
        Optional<Menu.MenuItemInfo> optionalMenuItemInfo = menu.select(name);
        if (optionalMenuItemInfo.isPresent()) {
            System.out.println(optionalMenuItemInfo.get());
        } else {
            System.out.println("Заявка с таким именем не найдена.");
        }
    }
}
