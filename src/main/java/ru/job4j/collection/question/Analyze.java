package ru.job4j.collection.question;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Analyze {

    public static Info diff(Set<User> previous, Set<User> current) {
        Map<Integer, String> tmpMapPrev = previous.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName));
        Map<Integer, String> tmpMapCurr = current.stream()
                .collect(Collectors
                        .toMap(User::getId, User::getName));
        Info rsl = new Info(0, 0, 0);
        for (User user : previous) {
            if (!current.contains(user)) {
                if (tmpMapCurr.get(user.getId()) == null) {
                    rsl.setDeleted(rsl.getDeleted() + 1);
                } else {
                    rsl.setChanged(rsl.getChanged() + 1);
                }
            }
        }
        for (User user : current) {
            if (!previous.contains(user) && tmpMapPrev.get(user.getId()) == null) {
                rsl.setAdded(rsl.getAdded() + 1);
            }
        }
        return rsl;
    }
}
