package dk.manaxi.unikpay.menus;

import dk.manaxi.unikpay.api.classes.Pakke;
import net.labymod.api.client.gui.mouse.MutableMouse;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.Activity;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.Bounds;
import net.labymod.api.client.gui.screen.widget.widgets.ComponentWidget;
import net.labymod.api.client.render.font.text.TextRenderer;
import net.labymod.api.client.render.matrix.Stack;
import java.awt.*;

@AutoActivity
public class Request extends Activity {
  private Pakke pakke;
  public Request(Pakke pakke) {
    this.pakke = pakke;
  }

  @Override
  public void render(Stack stack, MutableMouse mouse, float partialTicks) {
    super.render(stack, mouse, partialTicks);

    Bounds bounds = this.bounds();
    TextRenderer textRenderer = this.labyAPI.renderPipeline().textRenderer();
    textRenderer.text("I am a bare rendered example text")
        .pos(bounds.getCenterX(), bounds.getCenterY())
        .centered(true)
        .shadow(false)
        .color(Color.ORANGE.getRGB())
        .render(stack);
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);
  }
}
