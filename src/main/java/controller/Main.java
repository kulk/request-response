package controller;

public class Main {

    public static void main(String[] args) throws Exception{
        Request request = new Request();
        request.getRequest("https://www.it-omscholing.nl");
        request.responseHandler("https://www.it-omscholing.nl");

        ResponsiveRequest responsiveRequest =  new ResponsiveRequest();
        responsiveRequest.requestHandler("https://www.it-omscholing.nl");
    }
}
