package com.admin.catalago.infrastructure;

import com.admin.catalago.application.UseCase;

public class Main {
    public static void main(String[] args) {

        System.out.println(new UseCase().execute());
    }
}