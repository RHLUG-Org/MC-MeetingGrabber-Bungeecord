package siliconninja.meetinggrabber;


import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import com.google.gson.Gson;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;
import siliconninja.meetinggrabber.util.MeetingInfo;

public class Meeting extends Command{

	private MeetingGrabber plugin;
	public Meeting(MeetingGrabber plugin) {
		//TODO get config
		super("Meeting");
		this.plugin = plugin;
		
	}
 
	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer)sender;
			BaseComponent message = null;
			String url_string = (String) plugin.getConfig().get("url");
			try {
	            URL url = new URL(url_string);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");
	            connection.connect();
	            int response = connection.getResponseCode();
	            if (response != 200) {
	            	message = new TextComponent("HTTP Response Error: " + response);
	            	sender.sendMessage(message);
	            } else {
	                Scanner scan = new Scanner(url.openStream());
	                String json_data = "";
	                while(scan.hasNext()){
	                    json_data+=scan.next();
	                }
	                Gson gson = new Gson();
	                MeetingInfo currentMeeting = gson.fromJson(json_data, MeetingInfo.class);
	            	message = new TextComponent("The next meeting is at "+currentMeeting.getDate() + " "+ currentMeeting.getNumeric_date() +" from " + currentMeeting.getTime());
	            	sender.sendMessage(message);
	                scan.close();
	            }
	        } catch (Exception e) {
            	message = new TextComponent("Could not retrieve URL or Parse JSON");
            	sender.sendMessage(message);
	        }
		}
	}
}
