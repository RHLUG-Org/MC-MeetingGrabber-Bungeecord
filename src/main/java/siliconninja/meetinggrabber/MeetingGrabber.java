package siliconninja.meetinggrabber;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;


public class MeetingGrabber extends Plugin {
	private Configuration config;

	@Override
	public void onEnable() {
		ProxyServer.getInstance().getPluginManager().registerCommand(this, new Meeting(this));
		if(!getDataFolder().exists()) {
			getDataFolder().mkdir();
		}
		
		File file = new File(getDataFolder(), "config.yml");
		
            if (!file.exists()) {
                try (InputStream in = getResourceAsStream("config.yml")) {
                    Files.copy(in, file.toPath());
                } catch (IOException e) {
                    e.printStackTrace();
            }
        
//			url: "https://raw.githubusercontent.com/RHLUG-Org/RHLUG-Meeting-JSON/master/rest.json"
			
		}
		try {
			 config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
		} catch (IOException e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Configuration getConfig() {
		return config;
	}
}
