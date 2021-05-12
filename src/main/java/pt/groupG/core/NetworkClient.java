package pt.groupG.core;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

public class NetworkClient {
    private String address;
    private int port;
    private String baseURL;
    private Client client;

    public NetworkClient(String address, int port) {
        this.baseURL = "http://" + address + ":" + port + "/";
        this.address = address;
        this.port = port;
        // mudar biblioteca ->
        // register node as serializable.
        ClientConfig config = new ClientConfig(Node.class);
        config.register(JacksonJsonProvider.class);
        client = ClientBuilder.newClient(config);
    }

    public boolean ping() {
        return client.target(this.baseURL).path("ping").request(MediaType.APPLICATION_JSON_TYPE).get(Boolean.class);
    }

    public void join() {
        Join
    }

}
