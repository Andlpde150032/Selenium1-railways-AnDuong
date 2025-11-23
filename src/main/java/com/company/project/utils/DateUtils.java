package com.company.project.utils;

public class DateUtils {

    public static String getDynamicDate(String dateStr) {
        if (dateStr.startsWith("TODAY")) {
            try {
                String[] parts = dateStr.split("\\+");
                int daysToAdd = 0;
                if (parts.length > 1) {
                    daysToAdd = Integer.parseInt(parts[1]);
                }
                java.time.LocalDate date = java.time.LocalDate.now().plusDays(daysToAdd);
                return date.format(java.time.format.DateTimeFormatter.ofPattern("M/d/yyyy"));
            } catch (Exception e) {
                System.out.println("Error parsing dynamic date: " + e.getMessage());
                return dateStr;
            }
        }
        return dateStr;
    }
}
