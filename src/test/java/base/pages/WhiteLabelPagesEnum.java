package base.pages;


import base.core.listeners.TestOrderInterceptor;

public enum WhiteLabelPagesEnum {

    SEARCH_PAGE(10, "search"),
    SEARCH_RESULTS_PAGE(20, "results"),
    BOOKING_PAGE(30, "booking"),
    CONFIRMATION_PAGE(40, "confirmation"),
    MY_BOOKINGS_PAGE(50, "my-bookings");

    /**
     * execution order for {@link TestOrderInterceptor}
     */
    public final int order;
    public final String route;

    WhiteLabelPagesEnum(int order, String route) {
        this.order = order;
        this.route = route;
    }
}

