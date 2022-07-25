package entelect.training.incubator.spring.booking.service;

public class HelperFunctions {
    public static String referenceGenerator()
    {
        int n = 10;


        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder ref = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of ref
            ref.append(AlphaNumericString
                    .charAt(index));
        }

        return ref.toString();
    }

    public static String referenceGeneratorThirdParty()
    {
        int n = 16;


        // chose a Character random from this String
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        // create StringBuffer size of AlphaNumericString
        StringBuilder ref = new StringBuilder(n);

        for (int i = 0; i < n; i++) {

            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index
                    = (int)(AlphaNumericString.length()
                    * Math.random());

            // add Character one by one in end of ref
            ref.append(AlphaNumericString
                    .charAt(index));
        }

        return ref.toString();
    }
}
