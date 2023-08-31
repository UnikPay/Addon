package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import dk.manaxi.unikpay.menus.Request;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;

public class ChatListener {
  private final Main addon;

  public ChatListener(Main addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(ChatMessageSendEvent event) {
    if(event.getMessage().equals("test")) {
      Request request = new Request("test", "test", new Pakke[]{new Pakke(500, "test", "test")});
      addon.labyAPI().minecraft().executeNextTick(
          () -> addon.labyAPI().minecraft().minecraftWindow().displayScreen(request)
      );
    }
  }

}
