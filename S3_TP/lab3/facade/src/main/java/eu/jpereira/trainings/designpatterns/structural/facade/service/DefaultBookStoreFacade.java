package eu.jpereira.trainings.designpatterns.structural.facade.service;

import eu.jpereira.trainings.designpatterns.structural.facade.BookstoreFacade;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Book;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Customer;
import eu.jpereira.trainings.designpatterns.structural.facade.model.DispatchReceipt;
import eu.jpereira.trainings.designpatterns.structural.facade.model.Order;



public class DefaultBookStoreFacade implements BookstoreFacade {
    BookDBService bookDBService;
    CustomerDBService customerDBService;
    WharehouseService wharehouseService;
    OrderingService orderingService;
    CustomerNotificationService customerNotificationService;

    public DefaultBookStoreFacade () {}

    public DefaultBookStoreFacade(BookDBService bookDBService, 
                                  CustomerDBService customerDBService,
                                  WharehouseService wharehouseService, 
                                  OrderingService orderingService,
                                  CustomerNotificationService customerNotificationService) {
        this.bookDBService = bookDBService;
        this.customerNotificationService = customerNotificationService;
        this.customerDBService = customerDBService;
        this.wharehouseService = wharehouseService;
        this.orderingService = orderingService;
    }

    public void setBookDBService(BookDBService bookDBService) {
        this.bookDBService = bookDBService;
    }

    public void setCustomerDBService(CustomerDBService customerDBService) {
        this.customerDBService = customerDBService;
    }

    public void setCustomerNotificationService(CustomerNotificationService customerNotificationService) {
        this.customerNotificationService = customerNotificationService;
    }

    public void setWharehouseService(WharehouseService wharehouseService) {
        this.wharehouseService = wharehouseService;
    }

    public void setOrderingService(OrderingService orderingService) {
        this.orderingService = orderingService;
    }

    @Override
    public void placeOrder(String customerId, String isbn) {
        // The following would be possible if:
        // 1. The service package's interfaces had implementations (classes implementing them)
        // 2. The model package's classes had implementations

        // Customer customer = customerDBService.findCustomerById(customerId);
        // Book book = bookDBService.findBookByISBN(isbn);
        // Order order = orderingService.createOrder(customer, book);
        // DispatchReceipt dispatchReceipt = wharehouseService.dispatch(order);
        // customerNotificationService.notifyClient(dispatchReceipt);
    }
}
