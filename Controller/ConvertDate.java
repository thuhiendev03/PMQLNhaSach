/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 *
 * @author TieuSoi
 */


public class ConvertDate {

    private static ConvertDate instance;


    private ConvertDate() {
    }

    public static ConvertDate getInstance() {
        if (instance == null) {
            instance = new ConvertDate();
        }
        return instance;
    }

//    public Date changeFrom(String dateString) throws ParseException {
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date parsedDate = dateFormat.parse(dateString);
//        return parsedDate;
//    }
//      public String changeFromToString(String dateString) throws ParseException {
//        Date date = changeFrom(dateString);
//        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
//        return outputFormat.format(date);
//    }
//
//    public Date changeTo(String dateString) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//        Date parsedDate = dateFormat.parse(dateString);
//        return parsedDate;
//    }
    
     public Date changeFrom(String dateString) throws ParseException {
         if(dateString == null || dateString.isEmpty())
         {
             return null;
         }
         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parsedDate = dateFormat.parse(dateString);
        return parsedDate;
    }
      public String changeFromToString(String dateString) throws ParseException {
          if(dateString == null || dateString.isEmpty())
         {
             return " ";
         }
        Date date = changeFrom(dateString);
        if(date == null)
        {
            return "";
        }
        SimpleDateFormat outputFormat = new SimpleDateFormat("dd-MM-yyyy");
        return outputFormat.format(date);
    }

    public Date changeTo(String dateString) throws ParseException {
        if(dateString == null || dateString.isEmpty())
         {
             return null;
         }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date parsedDate = dateFormat.parse(dateString);
        return parsedDate;
    }
}