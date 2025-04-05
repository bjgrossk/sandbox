package sandbox;
import java.net.URL;

import java.net.HttpURLConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;


public class SimpleHTTPRequest {

    public static void main(String[] args) {
        int responseCode = 0;
        String text; 
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader; 
        

        try {            
            URL url = new URI("https://en.wikipedia.org/wiki/briangrossklas").toURL();
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("GET");

            responseCode = urlConnection.getResponseCode();
            System.out.println("\nResponse code is: " + responseCode);

            System.out.println("Header fields are: " + urlConnection.getHeaderFields().toString()); 
            System.out.println("Conent length is: " + urlConnection.getHeaderField("content-length")); 
            System.out.println("Conent type is: " + urlConnection.getHeaderField("content-type")); 

            if (responseCode >= 200 && responseCode < 299) {
                reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream())); 
                while ((text=reader.readLine()) != null) {
                    buffer.append(text);
                }
                System.out.println("\n Body is: " + url.getContent().toString());
                System.out.println ("\nString buffer is: " + buffer.toString());
                reader.close();
            }
           
            //Response code !=2xx, e.g. bad request
            else {
                System.out.println("Request failed: " + urlConnection.getResponseMessage());
            }

            urlConnection.disconnect();
        }

        catch(MalformedURLException murlx)   {
            murlx.printStackTrace(); 
        }
        catch(IllegalArgumentException iaex) {
            iaex.printStackTrace();
        }
        catch(URISyntaxException urisex) {
            urisex.printStackTrace();
        }
        catch(IOException ioException) {
            ioException.printStackTrace();
        }
    }
}