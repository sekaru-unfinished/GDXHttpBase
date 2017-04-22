package net.sleepystudios.ld38;

import com.badlogic.gdx.Net;
import com.badlogic.gdx.net.HttpStatus;
import com.badlogic.gdx.utils.Json;
import org.json.simple.JSONObject;

/**
 * Created by Tudor on 22/04/2017.
 */
public class Response {
    private Net.HttpResponse res;
    private String resObj;

    public Response(Net.HttpResponse res) {
        this.res = res;
        resObj = res.getResultAsString();
    }

    public int getStatusCode() {
        return res.getStatus().getStatusCode();
    }

    public boolean failed() {
        return getStatusCode() != HttpStatus.SC_OK;
    }

    public String getStr(Object key) {
        JSONObject obj = new Json().fromJson(JSONObject.class, resObj);
        return obj.get(key).toString();
    }
}
