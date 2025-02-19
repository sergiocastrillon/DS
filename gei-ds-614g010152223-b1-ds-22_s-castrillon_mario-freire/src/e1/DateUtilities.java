package e1;

public class DateUtilities {
    public static boolean isLeap(int year) {
        if ((year%4)==0 && (year%100)!=0) return true;
        else return (((year%100)==0) && ((year%400)==0));
    }


    public static int numberOfDays (int month, int year){
        if (month < 1 || month > 12) throw new IllegalArgumentException("Mes no válido");
        else{

            if (month == 2){
                // Si es año bisiesto devuelves 29 si no 28
                if (isLeap(year)) return 29;
                else return 28;
            }else{
                if (month<8){
                    if ((month%2)==0) return 30;
                    else return 31;
                }else{
                    if ((month%2)==0) return 31;
                    else return 30;
                }
            }
        }
    }


    public static String convertToISODate(String dateText) {
        String month;
        // Divides el string en partes que están separados por comas o espacios
        String[] parts = dateText.split("[, ]"); // Así funciona

        // Pasamos el mes a número
        month = switch (parts[0]) {
            case "January" -> "01";
            case "February" -> "02";
            case "March" -> "03";
            case "April" -> "04";
            case "May" -> "05";
            case "June" -> "06";
            case "July" -> "07";
            case "August" -> "08";
            case "September" -> "09";
            case "October" -> "10";
            case "November" -> "11";
            case "December" -> "12";
            default -> "00";
        };
        return parts[3] + "-" + month + "-" + parts[1];
    }

    public static boolean checkISODate(String ISODate){
        // Si el string mide menos de 10 no cumple el formato
        if (ISODate.length()<10) return false;
        int year,month,day;
        // Divides la fecha en partes separadas por "-"
        String[] fecha = ISODate.split("-");
        if(fecha.length!=3) return false; // Si la fecha no está compuesta por 3 partes devuelve falso
        try{ // Comprobamos que todos los valores introducidos son números
            year = Integer.parseInt(fecha[0]);
            month = Integer.parseInt(fecha[1]);
            day = Integer.parseInt(fecha[2]);
        }catch (final NumberFormatException e) {
            return false;
        }
        if (month > 12 || month < 1 || day > 31 || day < 1) return false;
        return numberOfDays(month,year) >= day;

        }

    }
