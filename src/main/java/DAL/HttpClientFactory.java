package DAL;

import org.springframework.stereotype.Component;

import java.net.http.HttpClient;
@Component
public class HttpClientFactory {
    public HttpClient getNewHttpClient() {
        return HttpClient.newHttpClient();
    }
}
