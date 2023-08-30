package dk.manaxi.unikpay.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.Minecraft;
import net.labymod.api.client.session.MinecraftAuthenticator;
import net.labymod.api.client.session.Session;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.NetworkLoginEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import java.util.concurrent.CompletableFuture;

public class JoinEvent {


  @Subscribe
  public void onJoin(ServerJoinEvent event) {
  


  }

}
