package order;

import order.ordervalidator.IOrderValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderValidatorSvc {
   private static OrderValidatorSvc instance;
   private final HashMap<String, List<IOrderValidator>> validationRules;

   private OrderValidatorSvc() {
      validationRules = new HashMap<>();
   }

   public static OrderValidatorSvc getInstance() {
      if(instance == null) {
         instance = new OrderValidatorSvc();
      }
      return instance;
   }

   public static void AddRule(String ruleContext, IOrderValidator rule) {
      OrderValidatorSvc svc = getInstance();

      if(!svc.validationRules.containsKey(ruleContext)) {
         List<IOrderValidator> ruleGroup = new ArrayList<>();
         svc.validationRules.put(ruleContext, ruleGroup);
      }

      List<IOrderValidator> rules = getInstance().validationRules.get(ruleContext);
      rules.add(rule);
   }

   public static void destroyInstance() {
      instance = null;
   }

   public void validate(Order order) {
      InvalidOrderMessageQueueSvc invalidMQ = InvalidOrderMessageQueueSvc.getInstance();

      if (order == null) {
         invalidMQ.add("Unable to validate order");
      }
      else {
         String ruleContext = order.getMenu();
         List<IOrderValidator> rulesInContext = validationRules.get(ruleContext);

         for (IOrderValidator rule : rulesInContext) {
            boolean rulePassed = rule.validate(order);
            if (!rulePassed) {
               rule.reportFailureToMessageQueue(order, invalidMQ);
            }
         }
      }
   }
}
