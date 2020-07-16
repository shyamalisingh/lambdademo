package top.careertrek.aws.lambda;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.MultipartStream;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Map;

public class UploadExcelHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {
    Gson gson = new GsonBuilder().create();

    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent event, Context context) {
        LambdaLogger logger = context.getLogger();
        APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();

        try{
            logger.log(event.getBody());
            String contentType = "";
            byte[] bI = Base64.decodeBase64(event.getBody().getBytes());

            //Get the content-type header and extract the boundary
            Map<String, String> hps = event.getHeaders();
            hps.entrySet().forEach(entry -> {
                logger.log("Header key "+entry.getKey()+" Header Value "+entry.getValue());
            });
            /*String[] boundaryArray = contentType.split("=");
            //Transform the boundary to a byte array
            byte[] boundary = boundaryArray[1].getBytes();
            *///Log the extraction for verification purposes
            logger.log(new String(bI, "UTF-8") + "\n");
            //Create a ByteArrayInputStream
            ByteArrayInputStream content = new ByteArrayInputStream(bI);
            //Create a MultipartStream to process the form-data
//            MultipartStream multipartStream = new MultipartStream(content, boundary, bI.length, null);
//            //Create a ByteArrayOutputStream
//            ByteArrayOutputStream out = new ByteArrayOutputStream();
//            //Find first boundary in the MultipartStream
//            boolean nextPart = multipartStream.skipPreamble();
//            //Loop through each segment
//            while (nextPart)
//            {
//                String header = multipartStream.readHeaders();
//                //Log header for debugging
//                logger.log("Headers:");
//                logger.log(header);
//                //Write out the file to our ByteArrayOutputStream
//                multipartStream.readBodyData(out);
//                //Get the next part, if any
//                nextPart = multipartStream.readBoundary();
//            }
            //Log completion of MultipartStream processing
            logger.log("Data written to ByteStream");
            //Prepare an InputStream from the ByteArrayOutputStream
            //InputStream fis = new ByteArrayInputStream(out.toByteArray());
            response.setStatusCode(200);
            response.setBody("Read excel of length "+event.getBody().length());
            return response;

        }catch (Exception ex){
            logger.log("Error while processing request " + ex.getMessage());
            response.setBody("Error while processing request " + ex.getMessage());
        }
        return response;
    }
}
