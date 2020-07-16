package top.careertrek.aws.lambda;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Test;

public class TVShowHandlerTest {
    @Test
    public void test_handle_tvshow(){
        System.setProperty("aws.accessKeyId","fakeMyKeyId");
        System.setProperty("aws.secretKey","fakeSecretAccessKey");
        System.setProperty("aws.endpoint","http://localhost:8000");
        Context context = new TestContext();
        DynamoDBService dynamoDBService = new DynamboDBServiceImpl();
        TvShowHandler handler = new TvShowHandler(dynamoDBService);
        TvShow tvShow = new TvShow();
        tvShow.setId("1");
        tvShow.setName("Seinfeld");
        String response = handler.handleRequest(tvShow,context);
        System.out.println(response);

    }
}
