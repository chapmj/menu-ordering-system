import menu.MenuSvcInit;
import order.*;
import order.ordervalidator.OrderValidatorSvc;
import order.ordervalidator.OrderValidatorSvcInit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        if(!args[0].equals("-i")) {
            MenuSvcInit.initialize();
            OrderValidatorSvcInit.initialize();

            OrderFactory orderFactory = new OrderFactory(new OrderFormatVerifier());
            for (String arg : args) {
                Order order = orderFactory.create(arg);
                OrderValidatorSvc.getInstance().validate(order);
                OrderPrinterSvc.getInstance().print(order);
            }
        }

        if(args[0].equals("-i")) {

            MenuSvcInit.initialize();
            OrderValidatorSvcInit.initialize();
            OrderFactory orderFactory = new OrderFactory(new OrderFormatVerifier());


            while(true) {
                System.out.print("In:  ");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                String rawOrder = null;
                try {
                    rawOrder = reader.readLine();
                } catch (IOException e) {
                    //e.printStackTrace();
                    System.exit(0);
                }

                if(rawOrder == null || rawOrder.equals("exit")) {
                    System.exit(0);
                }

                Order order = orderFactory.create(rawOrder);
                OrderValidatorSvc.getInstance().validate(order);
                System.out.print("Out: ");
                OrderPrinterSvc.getInstance().print(order);
                System.out.println();
            }
        }


    }

    public static void mainTest() {
        MenuSvcInit.initialize();
        OrderValidatorSvcInit.initialize();

        String rawOrder = "Breakfast 1,2, 3, 3,3";

        OrderFactory orderFactory = new OrderFactory(new OrderFormatVerifier());
        Order order = orderFactory.create(rawOrder);
        OrderValidatorSvc.getInstance().validate(order);
        OrderPrinterSvc.getInstance().print(order);
    }
}
