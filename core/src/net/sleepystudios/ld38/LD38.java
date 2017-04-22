package net.sleepystudios.ld38;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import org.json.simple.JSONObject;

import javax.swing.*;

public class LD38 extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
    BitmapFont font;
    ResponseListener rl;
    public static String name;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("background.bmp");
		font = new BitmapFont();
        rl = new ResponseListener();

		// add the new user
        String name = JOptionPane.showInputDialog(new JFrame(), "What's your name?");
		JSONObject obj = new JSONObject();
		obj.put("name", name);

		sendRequest("user", "POST", obj);
	}

	public void sendRequest(String route, String method, JSONObject reqObj) {
		Net.HttpRequest req = new Net.HttpRequest(method);
		String url = "http://localhost:3000/" + route;
		String content = new Json(JsonWriter.OutputType.json).toJson(reqObj);

        req.setUrl(url);
        req.setContent(content);
        req.setHeader("Content-Type", "application/json");
        req.setHeader("Accept", "application/json");

		Gdx.net.sendHttpRequest(req, rl);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);

		if(name!=null) {
		    font.draw(batch, "Your name is: " + name, 20, 30);
        }

		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
