package top.careertrek.aws.lambda;

import com.amazonaws.services.dynamodbv2.document.DynamoDB;

public interface DynamoDBService {
    DynamoDB getDB();
}
