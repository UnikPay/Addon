package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import dk.manaxi.unikpay.utils.NotificationUtil;
import dk.manaxi.unikpay.utils.RequestUtil;
import java.io.IOException;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.NamedScreen;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.bounds.ModifyReason;

@AutoActivity
@Link("request.lss")
public class RequestMenu extends SimpleActivity {
  private static final ModifyReason MODIFY_REASON = ModifyReason.of("Request");
  private final Pakke[] pakker;
  private final String id;
  private final String server;
  public RequestMenu(String server, String id, Pakke[] pakker) {
    this.pakker = pakker;
    this.id = id;
    this.server = server;
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    IconWidget iconWidget = new IconWidget( Icon.url("https://raw.githubusercontent.com/UnikPay/assets/main/500.png"));
    iconWidget.addId("server-icon");
    iconWidget.bounds().setSize(64, MODIFY_REASON);

    HorizontalListWidget listWidget = new HorizontalListWidget();
    for (Pakke pakke : pakker) {
      listWidget.addEntry(ComponentWidget.text(pakke.getName() + " for " + pakke.getPrice() + " ems").addId("pakke-navn") );
    }
    listWidget.addId("pakke-list");


    ComponentWidget componentWidget = ComponentWidget.text("§aØnsker du at godkende følgende anmodning?");
    componentWidget.addId("test-widget");
    ComponentWidget server = ComponentWidget.text("§aServer:");
    server.addId("server");
    ComponentWidget serverName = ComponentWidget.text(this.server);
    serverName.addId("server-name");

    ComponentWidget packages = ComponentWidget.text("§aPakke:");
    packages.addId("packages");

    ButtonWidget accept = ButtonWidget.text("§aAccept", () -> {
      try {
        RequestUtil.request(this.id, "accept");
        NotificationUtil.createNotification("Du har accepteret din anmodning");

      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }).addId("accept-button");

    ButtonWidget deny = ButtonWidget.text("§cAfvis", () -> {
      try {
        RequestUtil.request(this.id, "decline");
        Main.getInstance().labyAPI().minecraft().minecraftWindow().displayScreen(NamedScreen.MAIN_MENU.create());
        NotificationUtil.createNotification("Du har afvist din anmodning");
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }).addId("deny-button");

    this.document().addChildAsync(iconWidget);
    this.document().addChildAsync(componentWidget);
    this.document().addChildAsync(listWidget);
    this.document().addChildAsync(accept);
    this.document().addChildAsync(deny);
    this.document().addChildAsync(server);
    this.document().addChildAsync(serverName);
    this.document().addChildAsync(packages);


  }

}
