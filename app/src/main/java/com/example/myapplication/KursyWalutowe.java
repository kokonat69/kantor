//package com.example.myapplication;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class KursyWalutowe {
//
//    public static Map<String, Double> initKursyWalutowe() {
//        // Inicjalizacja kursów walutowych
//        Map<String, Double> kursyWalutowe = new HashMap<>();
//        // Dodawanie kursów walutowych dla poszczególnych par
//        kursyWalutowe.put("PLNPLN", 1.0); // Kurs PLN do PLNds
//        kursyWalutowe.put("PLNEUR", 0.23); // Kurs PLN do EUR
//        kursyWalutowe.put("PLNUSD", 0.26); // Kurs PLN do USD
//        kursyWalutowe.put("PLNJPY", 29.43); // Kurs PLN do JPY
//        kursyWalutowe.put("PLNGBP", 0.19); // Kurs PLN do GBP
//        kursyWalutowe.put("PLNCZK", 6.48); // Kurs PLN do CZK
//
//        // Dodawanie odwrotnych kursów walutowych (np. EUR do PLN)
//        kursyWalutowe.put("EURPLN", 1 / kursyWalutowe.get("PLNEUR"));
//        kursyWalutowe.put("USDPLN", 1 / kursyWalutowe.get("PLNUSD"));
//        kursyWalutowe.put("JPYPLN", 1 / kursyWalutowe.get("PLNJPY"));
//        kursyWalutowe.put("GBPPLN", 1 / kursyWalutowe.get("PLNGBP"));
//        kursyWalutowe.put("CZKPLN", 1 / kursyWalutowe.get("PLNCZK"));
//
//        // Dodaj pozostałe kursy walutowe
//
//        return kursyWalutowe;
//    }
//}