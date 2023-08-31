package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.api.classes.Pakke;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.mouse.MutableMouse;
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
@Link("example.lss")
public class Request extends SimpleActivity {
  private static final ModifyReason MODIFY_REASON = ModifyReason.of("Request");
  private final Pakke[] pakker;
  public Request(String server, String id, Pakke[] pakker) {
    this.pakker = pakker;
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
      listWidget.addEntry(ComponentWidget.text(
          pakke.getName()
      ).addId("pakke-navn"));
    }

    ComponentWidget componentWidget = ComponentWidget.text(
        "§aØnsker du at godkende følgende anmodning?"
    );
    componentWidget.addId("test-widget");

    ButtonWidget accept = ButtonWidget.text("§aAccept", () -> {

    }).addId("accept-button");
    ButtonWidget deny = ButtonWidget.text("§cAfvis", () -> {

    }).addId("deny-button");
    this.document().addChildAsync(iconWidget);
    this.document().addChildAsync(componentWidget);
    this.document().addChildAsync(listWidget);
    this.document().addChildAsync(accept);
    this.document().addChildAsync(deny);
  }
}
