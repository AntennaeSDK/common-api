package org.antennae.common.messages;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.UUID;

/**
 * <code>ClientMessage</code> is sent to a client.
 * A client is a mobile-app or IOT app.
 * OR it could be an user on several devices.
 * @See ClientAddress for more details.
 */
public class ClientMessage {

    /**
     * To where the message is sent.
     * it can be to an user or an app
     */
    private ClientAddress to;

    /** actual message */
    private String payLoad;

    /** The requestId is generated by the requester at the time of the original message.
     * it may be derived from a @See ServerMessage and passed on by the server in the ClientMessage
     */
    private String requestId;

    // TODO: use TypeAdapterFactory instead of passing the type.
    private String classType = ClientMessage.class.getName();

    /**
     * Broker decides the delivery of the message, based on the QOS type;
     */
    private ClientMessageQOSEnum messageQOS = ClientMessageQOSEnum.DIRECT_CONNECTION_ONLY;

    public ClientMessage(String requestId) {
        this.requestId = requestId;
    }
    public ClientMessage(){
        this.requestId = UUID.randomUUID().toString();
    }
    public ClientAddress getTo() {
        return to;
    }
    public void setTo(ClientAddress to) {
        this.to = to;
    }
    public String getPayLoad() {
        return payLoad;
    }
    public void setPayLoad(String payLoad) {
        this.payLoad = payLoad;
    }
    public String getRequestId() {
        return requestId;
    }
    public ClientMessageQOSEnum getMessageQOS() {
        return messageQOS;
    }
    public void setMessageQOS(ClientMessageQOSEnum messageQOS) {
        this.messageQOS = messageQOS;
    }

    public String toJson(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        return json;
    }
    public String toJsonPretty(){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(this);
        return json;
    }

    public static ClientMessage fromJson(String json ){
        Gson gson = new Gson();
        ClientMessage result = gson.fromJson( json, ClientMessage.class);
        return result;
    }

}
