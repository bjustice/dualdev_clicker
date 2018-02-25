package com.dualdev.clicker;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.dualdev.clicker.clickresources.ClickResource;
import com.dualdev.clicker.clickresources.WoodResource;

import javax.xml.soap.Text;
import java.util.List;

public class ClickerMain extends ApplicationAdapter {
    private static WoodResource woodResource;
    private Table table;
    private Stage stage;
    private Skin skin;
    private float scale;

	@Override
	public void create () {
	    scale = 4f;

        skin = new Skin(Gdx.files.internal("skin/flat-earth-ui.json"));
		stage = new Stage(new ExtendViewport(1200f / scale, 600f / scale));
		Gdx.input.setInputProcessor(stage);
		woodResource = new WoodResource();
		table = new Table();
		table.setFillParent(true);
        initializeWoodButtons();

        stage.addActor(table);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
	}

	private void initializeWoodButtons() {
        TextField woodCountText = createWoodCountText();
        TextField woodIncomeText = createWoodIncomeText();
        TextButton woodResourceButton = createWoodResourceButton(woodCountText);
        table.add(woodCountText).fillX().uniformX();
        table.row().pad(10,0,10,0);
        table.add(woodIncomeText).fillX().uniformX();
        table.row();
        table.add(woodResourceButton).fillX().uniformX();
    }

    private TextField createWoodCountText() {
	    TextField countArea = new TextArea("Total Wood: " + woodResource.getAmountStored(), skin);
	    countArea.setHeight(100f/scale);
	    countArea.setWidth(20f/scale);
	    countArea.setDisabled(true);
	    return countArea;
    }

    private TextField createWoodIncomeText() {
        TextField incomeArea = new TextArea("Wood Income: " + woodResource.getPerSecondIncome(), skin);
        incomeArea.setHeight(100f/scale);
        incomeArea.setWidth(20f/scale);
        incomeArea.setDisabled(true);
        return incomeArea;
    }

	private TextButton createWoodResourceButton(final TextField woodCount) {
	    TextButton woodTextButton = new TextButton("Click Here", skin);
        woodTextButton.setHeight(100f/scale);
        woodTextButton.setWidth(20f/scale);

        woodTextButton.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                int newTotal = woodResource.getAmountStored()+woodResource.getPerClickReturn();
                woodResource.setAmountStored(newTotal);
                woodCount.setText("Wood Income: " + newTotal);
            }
        });
	    return woodTextButton;
    }

	@Override
	public void dispose () {
	}
}
