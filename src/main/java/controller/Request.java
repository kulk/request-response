package controller;

import org.apache.http.Header;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

public class Request {

    /**
     * Schrijft een programma dat een ontvangen response analyseert en de
     * body (naam-waarde paren) presenteert.
     *
     * Dit programma stuurt een request en laat vervolgens de response
     * headers en response body zien.
     * **/

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    public void getRequest(String url) {
        HttpGet request = new HttpGet("https://www.it-omscholing.nl");
        System.out.println("HTTP request:\n" + request + "\n");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            System.out.println("Response headers:");
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                System.out.println(header);
            }
        } catch (IOException error){
            System.out.println(error.getMessage());
        }
    }

    public void responseHandler(String url) throws IOException {
        ResponseHandler<String> customResponseHandler =  new CustomResponseHandler();

        //Create an HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Create an HttpGet object
        HttpGet httpget = new HttpGet(url);

        //Execute the Get request by passing the response handler object and HttpGet object
        String httpResponse = httpclient.execute(httpget, customResponseHandler);

        System.out.println("\nResponse body:\n" + httpResponse);

    }


}
