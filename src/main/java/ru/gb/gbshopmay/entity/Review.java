package ru.gb.gbshopmay.entity;

import ru.gb.gbshopmay.entity.security.AccountUser;

/**
 * @author Artem Kropotov
 * created at 29.06.2022
 **/
public class Review {

    private AccountUser accountUser;

    private Product product;

    private String comment;

    private boolean approved;
}
