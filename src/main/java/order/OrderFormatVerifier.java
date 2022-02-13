package order;

import menu.IVerify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OrderFormatVerifier implements IVerify {


    public boolean verify(String rawOrder) {
        //<name> <id>, <id>, <id>...
        //An order consists of a meal and collection of comma separated item Ids.

        Pattern pattern = Pattern.compile("^[a-zA-Z]+(\\ ?)([0-9]+(, ?[0-9]+)*)?$");
        Matcher matcher = pattern.matcher(rawOrder);

        return matcher.matches() && !rawOrder.equals("");
    }
}
