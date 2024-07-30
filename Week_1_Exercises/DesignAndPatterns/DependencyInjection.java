interface CustomerRepository {
    String findCustomerById(String id);
}

class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public String findCustomerById(String id) {
        if (id.equals("1")) {
            return "John Doe";
        } else {
            return "Customer not found";
        }
    }
}

class CustomerService {
    private CustomerRepository repository;

    public CustomerService(CustomerRepository repository) {
        this.repository = repository;
    }

    public String getCustomerDetails(String id) {
        return repository.findCustomerById(id);
    }
}

public class DependencyInjection {
    public static void main(String[] args) {
        var repository = new CustomerRepositoryImpl();
        var service = new CustomerService(repository);
        var details = service.getCustomerDetails("1");
        System.out.println("Customer Details: " + details);
    }
}
