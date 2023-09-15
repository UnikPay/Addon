package dk.manaxi.unikpay.utils;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Type;

public class NotificationUtil {
  public static void createNofication(String message) {
    Notification.Builder builder = Notification.builder()
        .title(Component.text("UNIKPAY"))
        .text(Component.text(message))
        .type(Type.SYSTEM);
    Laby.labyAPI().notificationController().push(builder.build());
  }

}
