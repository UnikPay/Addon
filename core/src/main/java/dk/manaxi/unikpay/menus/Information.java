package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.Main;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.key.MouseButton;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.render.font.text.TextRenderer;
import net.labymod.api.client.render.matrix.Stack;
import java.awt.*;

@AutoActivity
public class Information extends Activity {

  private final Main addon;
  public Information() {
    this.addon = Main.getInstance();
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
    FlexibleContentWidget container = new FlexibleContentWidget();
    container.addId("information");
  }

  @Override
  public boolean mouseClicked(MutableMouse mouse, MouseButton mouseButton) {

    return false;
  }

}
