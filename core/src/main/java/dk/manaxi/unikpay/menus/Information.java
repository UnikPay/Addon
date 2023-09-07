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
  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    IconWidget iconWidget = new IconWidget(
        IoSocket.getAccount().getIconWidget()
    );
    iconWidget.addId("user-icon");
    iconWidget.bounds().setSize(64, MODIFY_REASON);

    FlexibleContentWidget container = new FlexibleContentWidget();
    container.addId("information");

    ComponentWidget componentWidget = ComponentWidget.text(
        "§aDu er logget ind som: §e" + IoSocket.getAccount().getUsername()
    );
    componentWidget.addId("name-widget");



    this.document().addChildAsync(componentWidget);
    this.document().addChildAsync(iconWidget);


  }

  @Override
  public boolean mouseClicked(MutableMouse mouse, MouseButton mouseButton) {

    return false;
  }



}
