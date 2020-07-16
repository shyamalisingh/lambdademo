package top.careertrek.aws.lambda;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CustomerHandler implements RequestHandler<Customer,String> {
    Gson gson = new GsonBuilder().create();
    private DynamoDBService dynamoDBService;

    public CustomerHandler(DynamoDBService dynamoDBService) {
        this.dynamoDBService = dynamoDBService;
    }

    @Override
    public String handleRequest(Customer customer, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Customer inserted " +customer);
        DynamoDB dynamoDB = dynamoDBService.getDB();
        Table table = dynamoDB.getTable("Customers");
        logger.log("Got table "+table);
        Item item = Item.fromJSON(gson.toJson(customer));
        table.putItem(item);
        return String.format("Customer with Id %s inserted successfully",customer.getId());
    }


}
