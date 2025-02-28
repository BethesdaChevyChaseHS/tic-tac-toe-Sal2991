package bcc.tictactoe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayerSelectionScreen extends ScreenAdapter{
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public PlayerSelectionScreen(TicTacToe game, int curPlayer) {//checkpoint 1
       //load skin

       //add title saying something like "select player"

       //if you would like a background color behind the title, you can use the helper method in the Constants file

       //check out the documentation linked in the readme / on canvas

       //add buttons to select from the player types listed in constants.java. If there isSimulated is true, don't let human be an option. 
    
       //curplayer will either be 0 or 1
       this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));

        Container<Label> selectLabel = Constants.createLabelWithBackgrounColor("Select Player " + currentPlayer, Color.BLACK, skin);
        Table table = new Table();
        table.add(selectLabel).pad(5).row();

        if (!game.getIsSimulated()) {
            addButton(table, "Human", "human", currentPlayer);
        }

        addButton(table, "random AI", "random", currentPlayer);
        addButton(table, "smart AI", "smart", currentPlayer);
        addButton(table, "smarter AI", "genius", currentPlayer);

        table.setFillParent(true);
        table.center();
        stage.addActor(table);
    }

    private void addButton(Table table, String buttonText, String playerType, int currentPlayer) {
        TextButton button = new TextButton(buttonText, skin);
        table.add(button).pad(5).row();
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setPlayer(currentPlayer, playerType);
            }
        });
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }
}
