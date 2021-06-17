package com.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BusinessOnline {
    public static void main(String[] args) {
        String url = "https://m.business-gazeta.ru/";
        String regex = "\"last-news__link\">[A-zА-я0-9\\s«»:\"-.–]{3,}|</a><a";
        StringBuilder sb = new StringBuilder();
        Pattern p = Pattern.compile(regex);

        InputStream response;

        try {
            response = new URL(url).openStream();
            Scanner b = new Scanner(response);

            while (b.hasNextLine()) sb.append(b.nextLine());
            b.close();

            String temp = sb.toString();//это еще сырой текст, просто одна строка без применения чего-нибудь

            Matcher m = p.matcher(temp);
            StringBuilder sb2 = new StringBuilder();
            while (m.find()) {
                sb2.append(m.group().replaceAll("\"last-news__link\">|</a><a", "").strip()).append("\n");
            }
            String si = sb2.toString().replace("\n\n", "\n");
            System.out.println(si);

            response.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
