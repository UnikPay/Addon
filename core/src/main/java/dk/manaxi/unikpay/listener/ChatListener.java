package dk.manaxi.unikpay.listener;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import dk.manaxi.unikpay.menus.RequestMenu;
import dk.manaxi.unikpay.webscoket.IoSocket;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatMessageSendEvent;

public class ChatListener {
  private final Main addon;

  public ChatListener(Main addon) {
    this.addon = addon;
  }

  @Subscribe
  public void onGameTick(ChatMessageSendEvent event) {

  }

}
