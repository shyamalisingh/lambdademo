package top.careertrek.aws.lambda;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class TvShowHandler implements RequestHandler<TvShow,String> {
    Gson gson = new GsonBuilder().create();
    private DynamoDBService dynamoDBService;

    public TvShowHandler(DynamoDBService dynamoDBService) {
        this.dynamoDBService = dynamoDBService;
    }

    @Override
    public String handleRequest(TvShow tvShow, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Tv Show inserted " +tvShow);
        DynamoDB dynamoDB = dynamoDBService.getDB();
        Table table = dynamoDB.getTable("TVShows");
        logger.log("Got table "+table);
        Item item = Item.fromJSON(gson.toJson(tvShow));
        table.putItem(item);
        return String.format("Customer with Id %s inserted successfully",tvShow.getId());
    }


}
