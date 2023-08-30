package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import dk.manaxi.unikpay.menus.Request;
import net.labymod.api.Laby;
import net.labymod.api.LabyAPI;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;
import net.labymod.api.event.client.chat.ChatReceiveEvent;

public class ChatListener {
  private final Main addon;

  public ChatListener(Main addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(ChatMessageSendEvent event) {
    if(event.getMessage().equals("test")) {
      ((Request) addon.labyAPI().ingameOverlay().getActivity(Request.class).get()).setVisible(true);
    }
  }

}
