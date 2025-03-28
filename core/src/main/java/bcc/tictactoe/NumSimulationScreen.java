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
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class NumSimulationScreen extends ScreenAdapter {
    private final TicTacToe game;
    private Stage stage;
    private Skin skin;

    public NumSimulationScreen(TicTacToe game) {
        this.game = game;
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        skin = new Skin(Gdx.files.internal("skins/glassy/glassy-ui.json"));
        

        //checkpoint 3 - add more stuff!

        Texture backgroundTexture = new Texture(Gdx.files.internal("space_tictactoe.png"));
        Image backgroundImage = new Image(backgroundTexture);
        backgroundImage.setFillParent(true);
        stage.addActor(backgroundImage);

        Container<Label> titleLabel = Constants.createLabelWithBackgrounColor("How Many Rounds?", Color.MAROON, skin);
        titleLabel.setPosition(250, 400);
        stage.addActor(titleLabel);

    
        TextField roundsInput = new TextField("", skin);
        roundsInput.setMessageText("Enter # of rounds");
       
        stage.addActor(roundsInput);

        TextButton continueButton = new TextButton("Keep going", skin);
        continueButton.setPosition(180, 0);
        continueButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x,float y) {
                int rIn = Integer.parseInt(roundsInput.getText());
                game.setNumberOfRounds(rIn);
                System.out.println(game.getRound());
                game.setScreen(new GameDisplay(game));
            }
        });
        stage.addActor(continueButton);
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