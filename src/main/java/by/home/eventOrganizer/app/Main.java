package by.home.eventOrganizer.app;

import by.home.eventOrganizer.config.AppConfiguration;
import by.home.eventOrganizer.model.detail.Address;
import by.home.eventOrganizer.model.detail.enums.City;
import by.home.eventOrganizer.model.human.Customer;
import by.home.eventOrganizer.model.human.Staff;
import by.home.eventOrganizer.model.human.enums.Department;
import by.home.eventOrganizer.service.human.CustomerService;
import by.home.eventOrganizer.service.human.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class Main {

        @Autowired
        private StaffService staffService;

        @Autowired
        private CustomerService customerService;

    public static void main(String[] arg) {
        AnnotationConfigApplicationContext annotatedClassApplicationContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        String[] beanDefinitionNames = annotatedClassApplicationContext.getBeanDefinitionNames();
        System.out.println(Arrays.toString(beanDefinitionNames));
        Main main = annotatedClassApplicationContext.getBean("main", Main.class);

        List<Staff> staff = main.staffService.findByPhoneNumber(375297642865L);
        staff.stream().forEach(System.out::println);
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

    private static Customer createCustomer(String name, Long phone, Address address) {
        Customer persistCustomer = new Customer();
        persistCustomer.setName(name);
        persistCustomer.setPhoneNumber(phone);
        persistCustomer.setSumma(15.00);
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
