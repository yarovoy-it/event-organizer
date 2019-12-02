package by.home.eventOrganizer.app;

import by.home.eventOrganizer.config.AppConfiguration;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.model.detail.enums.City;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.service.detail.AddressService;
import by.home.eventOrganizer.service.detail.OrderService;
import by.home.eventOrganizer.service.human.CustomerService;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class Main {

        @Autowired
        private StaffService staffService;

        @Autowired
        private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private OrderService orderService;

    public static void main(String[] arg) {
        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        String[] beanDefinitionNames = annotatedClassApplicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        Main main = annotatedClassApplicationContext.getBean("main", Main.class);

//        List<Integer> integers =Arrays.asList(14,25,346,547);
//        Double  price = integers.stream().flatMapToDouble(number -> DoubleStream.of(Double.valueOf(number))).sum();
//        System.out.println(price);

//        Order price = main.orderService.findById(57L).get();
//        System.out.println(price.toString());
//        main.orderService.saveTransientOrder(price);
//        System.out.println(price);

//        Order order = new Order();
//        order.setAddress(main.addressService.save(createAddress("Limoza", 45, 145)));
//        Set<Staff> staff = new HashSet<>();
//        staff.add(main.staffService.findById(16L).orElseGet(Staff::new));
//        staff.add(main.staffService.findById(20L).orElseGet(Staff::new));
//        order.setStaff(staff);
//        order.setCustomer(main.customerService.findById(27L).orElseGet(Customer::new));
//
//        System.out.println(order.toString());
//        main.orderService.save(order);
//        Customer customer = createCustomer("Masha" , "Petichkina" , 375337885746L, address);
//        main.customerService.save(customer);
//        main.staffService.deleteById(22L);
//
//        List<Staff> addresses = main.staffService.findAll();
//        addresses.stream().forEach(System.out::println);
//     main.staffService.deleteById(17L);

        Staff staff = main.staffService.findById(19L).orElseGet(Staff::new);
        System.out.println(staff);
//        Department depBar = Department.valueOf("bartender".toUpperCase());
//        List<Staff> staff = main.staffRepository.findByDepartment(depBar);
//        staff.stream().forEach(System.out::println);



/*



        main.addressRepository.save(createAddress("17may", 45, 6));
        List<Address> addresses = main.addressRepository.findAll();
        addresses.stream().forEach(System.out::println);
*/
    }

    private static Staff createStaff(String name, String surname, Long phone) {
        Staff transientStaff = new Staff();

        transientStaff.setName(name);
        transientStaff.setSurname(surname);
        transientStaff.setPhoneNumber(phone);
        transientStaff.setDepartment(Department.WAITER);
        transientStaff.setSalary(20.90);
        return transientStaff;

    }

    private static Staff createStaff(String name, String surname, Long phone, Address address) {
        Staff transientStaff = new Staff();
        transientStaff.setAddress(address);
        transientStaff.setName(name);
        transientStaff.setSurname(surname);
        transientStaff.setPhoneNumber(phone);
        transientStaff.setDepartment(Department.PLANNER);
        transientStaff.setSalary(40.50);
        return transientStaff;

    }

    private static Customer createCustomer(String name, String surname, Long phone, Address address) {
        Customer persistCustomer = new Customer();
        persistCustomer.setName(name);
        persistCustomer.setSurname(surname);
        persistCustomer.setPhoneNumber(phone);
        persistCustomer.setSumma(0.00);
        persistCustomer.setAddress(address);
        return persistCustomer;
    }

    private static Address createAddress(String str, Integer houseNumber, Integer appartment) {
        Address transientAddress = new Address();
        transientAddress.setCity(City.GRODNO);
        transientAddress.setHouseNumber(houseNumber);
        transientAddress.setStreet(str);
        transientAddress.setApartment(appartment);
        return transientAddress;
    }
}
