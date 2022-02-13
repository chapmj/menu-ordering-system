package order;

import order.ordervalidator.*;

public class OrderValidatorSvcInit {
    public static void initialize() {

        //breakfast
        String breakfastContext = "Breakfast";
        OrderValidatorSvc.AddRule(breakfastContext, new RequireMainCourseValidator());
        OrderValidatorSvc.AddRule(breakfastContext, new RequireSideCourseValidator());
        OrderValidatorSvc.AddRule(breakfastContext, new RestrictToOneSideCourseValidator());
        OrderValidatorSvc.AddRule(breakfastContext, new RequireDrinkCourseValidator());

        //lunch
        String lunchContext = "Lunch";
        OrderValidatorSvc.AddRule(lunchContext, new RequireMainCourseValidator());
        OrderValidatorSvc.AddRule(lunchContext, new RequireSideCourseValidator());
        OrderValidatorSvc.AddRule(lunchContext, new RequireDrinkCourseValidator());
        OrderValidatorSvc.AddRule(lunchContext, new RestrictDuplicateMainCourseValidator());

        //dinner
        String dinnerContext = "Dinner";
        OrderValidatorSvc.AddRule(dinnerContext, new RequireMainCourseValidator());
        OrderValidatorSvc.AddRule(dinnerContext, new RequireSideCourseValidator());
        OrderValidatorSvc.AddRule(dinnerContext, new RequireDrinkCourseValidator());
        OrderValidatorSvc.AddRule(dinnerContext, new RequireWaterValidator());
        OrderValidatorSvc.AddRule(dinnerContext, new RequireDessertCourseValidator());
        OrderValidatorSvc.AddRule(dinnerContext, new RestrictToOneSideCourseValidator());

    }
}
