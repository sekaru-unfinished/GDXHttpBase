package net.sleepystudios.ld38;

import com.badlogic.gdx.Net;

/**
 * Created by Tudor on 22/04/2017.
 */
public class ResponseListener implements Net.HttpResponseListener {
    private Response res;

    @Override
    public void handleHttpResponse(Net.HttpResponse httpResponse) {
        res = new Response(httpResponse);


        String head = res.getStr("head");
        if(!res.failed()) {
            if(head.equals("login_accept")) {
                LD38.name = res.getStr("name") + ", id: " + res.getStr("ip");
            }
        }
    }

    @Override
    public void failed(Throwable t) {
        System.out.println("Request failed: " + t);
    }

    @Override
    public void cancelled() {
        System.out.println("Request cancelled");
    }
}
