package siliconninja.meetinggrabber;


import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Meeting extends Command{

	public Meeting(MeetingGrabber plugin) {
		//TODO get config
		super("Meeting");
	}
 
	@Override
	public void execute(CommandSender sender, String[] args) {
		if((sender instanceof ProxiedPlayer)) {
			ProxiedPlayer p = (ProxiedPlayer)sender;
			BaseComponent message = new TextComponent("The next meeting is on ");
			sender.sendMessage(message);
		}
	}
}
