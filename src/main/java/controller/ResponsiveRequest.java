package controller;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponsiveRequest {

    /**
     * Schrijft  een programma dat een serie requests verstuurt waarbij iedere
     * request af hangt van de voorgaande response.
     *
     * Dit programma analyseerd de body van een response en set alle voorkomende URL's
     * in een lijst. De client stuurt vervolgens weer een request naar de eerste URL
     * die niet gelijk is aan de url waar de vorige request aan verstuurd is.
     * **/

    public void requestHandler(String url) throws IOException{
        for (int i = 0; i < 3; i++) {
            System.out.println("Request URL:\n" + url + "\n\n");
            String response = responseHandler(url);
            System.out.println("Response body (partly):\n"+ response.substring(0, 400) + "\n");
            ArrayList<String> uriList = getURIs(response);
            url = findNextNotEqual(uriList, url);
        }

    }
    private String findNextNotEqual(ArrayList<String> uriList, String url){
        for(String uri : uriList){
            if(!uri.equals(url)){
                return uri;
            }
        }
        return "";
    }
    private String responseHandler(String url) throws IOException {
        ResponseHandler<String> customResponseHandler =  new CustomResponseHandler();

        //Create an HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Create an HttpGet object
        HttpGet httpget = new HttpGet(url);

        //Execute the Get request by passing the response handler object and HttpGet object
        String httpResponse = httpclient.execute(httpget, customResponseHandler);
        return  httpResponse;
    }
    private ArrayList<String> getURIs(String httpResponse){
        String regex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(httpResponse);
        ArrayList<String> uriList = new ArrayList<>();
        while(matcher.find()) {
            uriList.add(matcher.group(0));
        }
        return uriList;
    }

}
