package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;

public class RoleStoreTest {
    @Test
    public void whenAddAndFindThenRoleNameIsPovar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Povar"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        Role result = store.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindRolenameIsPovar() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        store.add(new Role("1", "Maxim"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Povar"));
    }

    @Test
    public void whenReplaceThenUsernameIsProgrammer() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        store.replace("1", new Role("1", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Programmer"));
    }

    @Test
    public void whenNoReplaceUserThenNoChangeUsername() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        store.replace("10", new Role("10", "Programmer"));
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Povar"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        store.delete("1");
        Role result = store.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Povar"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getRoleName(), is("Povar"));
    }
}
