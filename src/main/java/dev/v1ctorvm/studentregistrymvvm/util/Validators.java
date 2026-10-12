package dev.v1ctorvm.studentregistrymvvm.util;

public final class Validators {

    public Validators() {

    }

    public static boolean notBlank(String s){
        return s != null && !s.trim().isBlank();
    }

    public static boolean email(String s){
       if (!notBlank(s)) return false;

       int at = s.indexOf("@");
       int dot = s.lastIndexOf(".");

        return at > 0
                && dot > at + 1
                && dot < s.length() -1;

    }

    public static boolean inRange(Double grade, int min, int max){

        if (grade.isNaN()) return false;

        return  grade >= min && grade <= max;
    }
}
