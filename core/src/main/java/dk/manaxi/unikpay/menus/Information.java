package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.Main;
import dk.manaxi.unikpay.user.Account;
import dk.manaxi.unikpay.webscoket.IoSocket;
import io.socket.client.IO;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.render.matrix.Stack;
import net.labymod.api.util.bounds.ModifyReason;


@AutoActivity
@Link("information.lss")
public class Information extends Activity {
  private static final ModifyReason MODIFY_REASON = ModifyReason.of("Request");
  Account account = IoSocket.getAccount();
  Icon icon = (account != null) ? account.getIconWidget() : Icon.head("Unikpay");
  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    IconWidget iconWidget = new IconWidget(
        icon
    );
    iconWidget.addId("user-icon");
    iconWidget.bounds().setSize(64, MODIFY_REASON);

    FlexibleContentWidget container = new FlexibleContentWidget();
    container.addId("information");

    String username = (account != null && account.getUsername() != null) ? account.getUsername() : "Unikpay";
    String balanceFormatted = (account != null && account.getBalanceFormatted() != null) ? account.getBalanceFormatted() : "0";

    // Create ComponentWidget objects
    ComponentWidget componentWidget = ComponentWidget.text("§aDu er logget ind som: §e" + username);
    componentWidget.addId("name-widget");

    ComponentWidget balanceWidget = ComponentWidget.text("§aDin balance er: §e" + balanceFormatted);

    balanceWidget.addId("balance-widget");




    this.document().addChildAsync(componentWidget);
    this.document().addChildAsync(balanceWidget);
    this.document().addChildAsync(iconWidget);


  }

  @Override
  public boolean mouseClicked(MutableMouse mouse, MouseButton mouseButton) {

    return false;
  }

  @Override
  public void onCloseScreen() {
    super.onCloseScreen();
    IoSocket.getSocket().emit("information");
  }

  @Override
  public void onOpenScreen() {
    super.onOpenScreen();
    IoSocket.getSocket().emit("information");
  }
}
