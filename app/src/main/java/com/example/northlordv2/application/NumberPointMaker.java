package com.example.northlordv2.application;

public class NumberPointMaker {
    public static String makePoints(String s){
        char[] array=deletePoints(s).toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        int counter=0;
        for (int i=array.length-1;i>=0;i--){
            if(counter==3){
                stringBuilder.append(" ");
                counter=0;
            }
            stringBuilder.append(array[i]);
            counter++;
        }
        return stringBuilder.reverse().toString();
    }
    public static String deletePoints(String s){
        char[] array=s.toCharArray();
        StringBuilder builder=new StringBuilder();
        for(int i=0;i<array.length;i++){
            if((array[i]=='1'|array[i]=='2'|array[i]=='3'|array[i]=='4'|array[i]=='5'|array[i]=='6'|array[i]=='7'|array[i]=='8'|array[i]=='9'|array[i]=='0')){
                builder.append(array[i]);
            }
        }
        return builder.toString();
    }
}
