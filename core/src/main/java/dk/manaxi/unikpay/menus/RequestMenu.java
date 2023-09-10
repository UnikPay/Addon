package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.api.classes.Pakke;
import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.NamedScreen;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.notification.Notification;
import net.labymod.api.notification.Notification.Type;
import net.labymod.api.util.bounds.ModifyReason;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import java.io.IOException;

@AutoActivity
@Link("example.lss")
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

    IconWidget iconWidget = new IconWidget(
        Icon.url("https://raw.githubusercontent.com/UnikPay/assets/main/500.png")
    );
    iconWidget.addId("server-icon");
    iconWidget.bounds().setSize(64, MODIFY_REASON);

    HorizontalListWidget listWidget = new HorizontalListWidget();
    for (Pakke pakke : pakker) {
      listWidget.addEntry(ComponentWidget.text(pakke.getName() + " for " + pakke.getPrice() + " ems") .addId("pakke-navn") );

    }
    listWidget.spaceLeft().set(440.00F);
    listWidget.setTranslateY(265.5F);


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
        acceptRequest(this.id);


        Notification.Builder builder = Notification.builder()
            .title(Component.text("UNIKPAY"))
            .text(Component.text("Du har accepteret din anmodning"))
            .type(Type.ADVANCEMENT);
        Laby.labyAPI().notificationController().push(builder.build());


      } catch (IOException e) {
        throw new RuntimeException(e);
      }

    }).addId("accept-button");

    ButtonWidget deny = ButtonWidget.text("§cAfvis", () -> {
      try {
        denyRequest(this.id);
        Main.getInstance().labyAPI().minecraft().minecraftWindow().displayScreen(NamedScreen.MAIN_MENU.create());
        Notification.Builder builder = Notification.builder()
            .title(Component.text("UNIKPAY"))
            .text(Component.text("Du har afvist din anmodning"))
            .type(Type.SYSTEM);
        Laby.labyAPI().notificationController().push(builder.build());
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

  @Override
  public void onCloseScreen() {
    super.onCloseScreen();
    try {
      denyRequest(this.id);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    //Bruges til at deny requesten også
  }




  private void acceptRequest(String id) throws IOException {

    OkHttpClient client = new OkHttpClient();
    String url = "https://unikpay.manaxi.dk/v1/request/" + id + "/accept";

    okhttp3.Request request = new okhttp3.Request.Builder()
        .url(url)
        .post(RequestBody.create(MediaType.parse("application/json"), ""))
        .header("Authorization", "69277278-873a-4edf-bfc0-3d1ebc7550d5")
        .build();
    Main.getInstance().labyAPI().minecraft().minecraftWindow().displayScreen(NamedScreen.MAIN_MENU.create());
    Response response = client.newCall(request).execute();
    int responseCode = response.code();

  }
  private void denyRequest(String id) throws IOException {
    OkHttpClient client = new OkHttpClient();
    String url = "https://unikpay.manaxi.dk/v1/request/" + id + "/decline";

    okhttp3.Request request = new okhttp3.Request.Builder()
        .url(url)
        .post(RequestBody.create(MediaType.parse("application/json"), ""))
        .header("Authorization", "69277278-873a-4edf-bfc0-3d1ebc7550d5")
        .build();
    Response response = client.newCall(request).execute();
    System.out.println(response);
    System.out.println(response.code());

  }

}
