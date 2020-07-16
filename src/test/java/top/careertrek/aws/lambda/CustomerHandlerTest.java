package top.careertrek.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Test;

public class CustomerHandlerTest {
    @Test
    public void test_handle_customer(){
        System.setProperty("aws.accessKeyId","fakeMyKeyId");
        System.setProperty("aws.secretKey","fakeSecretAccessKey");
        System.setProperty("aws.endpoint","http://localhost:8000");
        Context context = new TestContext();
        DynamoDBService dynamoDBService = new DynamboDBServiceImpl();
        CustomerHandler handler = new CustomerHandler(dynamoDBService);
        Customer customer = new Customer();
        customer.setName("Test");
        customer.setId("Test_Id");
        Address address = new Address();
        address.setCity("Bangalore");
        customer.setAddress(address);
        customer.setAge(21);
        String response = handler.handleRequest(customer,context);
        System.out.println(response);

    }
}
