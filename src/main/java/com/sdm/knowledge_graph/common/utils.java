package com.sdm.knowledge_graph.common;

import java.lang.String;

public class utils {

    public static String str_clean(String str) {
        return str
        .replace("; ", ";")
        .replaceAll("[^A-Za-z0-9] .","")
        .replace(" ", "_")
        ;
    }

    public static void print(Object text) { System.out.println(text); }

	public static void log(String text) { print("[ log ] " + text); }
	
    public static void error(String text) { print("[ error ] " + text); System.exit(1); }
	
	public static void line_separator() { print("\n----------------------------------\n"); }

}