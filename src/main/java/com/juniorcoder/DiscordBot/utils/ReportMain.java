package com.juniorcoder.DiscordBot.utils;

public class ReportMain {
    public static void main(String[] args) {
        UserReport userReport = new UserReport();
        String[] labels = new String[]{"Id", "Description", "State", "Description", "Puente", "Direccion", "Id", "Description", "State", "Description", "Puente", "Direccion"};
        userReport.setColLabelArray(labels);
        userReport.generateReport();
    }
}
