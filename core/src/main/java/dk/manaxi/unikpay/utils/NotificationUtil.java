package dk.manaxi.unikpay.utils;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.webscoket.IoSocket;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Type;

public class NotificationUtil {
  public static void createNotification(String message) {
    IoSocket.getSocket().emit("information");
    Notification.Builder builder = Notification.builder()
        .title(Component.text("UNIKPAY"))
        .text(Component.text(message))
        .icon(Main.hudIcon)
        .type(Type.SYSTEM);
    Laby.labyAPI().notificationController().push(builder.build());
  }

}
