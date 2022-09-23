package e1;

public class DateUtilities {
    public static boolean isLeap(int year) {
        if((year%4)==0 && (year%100)!=0) return true;
        else return(((year%100)==0) && ((year%400)==0));
    }


    public static int numberOfDays (int month, int year){
        if(month < 1 || month > 12) throw new IllegalArgumentException("Mes no válido");
        else{
            switch (month){
                case 2:
                    if(isLeap(year)) return 29;
                    else return 28;
                case 8:
                    return 31;
                default:
                    if(month<8){
                        if((month%2)==0) return 30;
                        else return 31;
                    }else{
                        if((month%2)==0) return 31;
                        else return 30;
                    }
        }

        }
    }


    public static String convertToISODate(String dateText) {
        String month;
        String[] parts = dateText.split("[, ]"); // Creo que asi funciona

        switch (parts[0]) {
            case "January":
                month = "01";
                break;
            case "February":
                month = "02";
                break;
            case "March":
                month = "03";
                break;
            case "April":
                month = "04";
                break;
            case "May":
                month = "05";
                break;
            case "June":
                month = "06";
                break;
            case "July":
                month = "07";
                break;
            case "August":
                month = "08";
                break;
            case "September":
                month = "09";
                break;
            case "October":
                month = "10";
                break;
            case "November":
                month = "11";
                break;
            case "December":
                month = "12";
                break;
            default:
                month = "00";
        }
        return parts[3] + "-" + month + "-" + parts[1];
    }

    public static boolean checkISODate(String ISODate){
        if(ISODate.length()<10) return false;
        int year,month,day;
        String[] fecha = ISODate.split("-");
        if(fecha.length!=3) return false;
        try{ // Comprobamos que todos los valores introducidos son números
            year = Integer.parseInt(fecha[0]);
            month = Integer.parseInt(fecha[1]);
            day = Integer.parseInt(fecha[2]);
        }catch (final NumberFormatException e){
            return false;
        }
        if(month > 12 || month < 1 || day > 31 || day < 1) return false;
        return !(numberOfDays(month,year)<day);

        }

    }
