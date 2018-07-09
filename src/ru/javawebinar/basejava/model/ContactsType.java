package ru.javawebinar.basejava.model;

public enum ContactsType {
    Phone("Телефон"),
    Skype("Skype"),
    Mail("Почта"),
    LinkedIn("Профиль LinkedIn"),
    GitHub("Профиль GitHub"),
    Stackoverflow("Профиль Stackoverflow"),
    Homepage("Домашняя страница");

    private String title;

    ContactsType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

