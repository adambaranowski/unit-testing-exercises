package pl.adambaranowski.testing.homework;

public class code2 {
    public static String solution(String hour) {

        int hInt = Integer.parseInt(hour.substring(0, 2));
        int mInt = Integer.parseInt(hour.substring(3, 5));

        int revMin;
        int revHour;

        if (mInt == 0) {
            revMin = 0;
        } else {
            revMin = 60 - mInt;
        }

        if (hInt == 0) {
            //case of midnight and minutes bigger than 30
          //  if(mInt<30){
            //    revHour = 11;
           // }else{
                revHour = 0;
           // }

        } else {
            revHour = 12 - hInt;
        }

         if(mInt<30){
            revHour -= 1;
         }
         if(revHour<0){
             revHour = 11;
         }


        //formatting to final return format
        StringBuilder sb = new StringBuilder();

        if (revHour < 10) {
            sb.append("0");
            sb.append(revHour);
        } else {
            sb.append(revHour);
        }

        sb.append(":");

        if (revMin < 10) {
            sb.append("0");
            sb.append(revMin);
        } else {
            sb.append(revMin);
        }


        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(solution("01:41"));
    }
}