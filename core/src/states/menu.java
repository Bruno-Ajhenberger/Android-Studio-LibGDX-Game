package states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.MyGdxGame;

public class menu extends state{
	
	public static final int screenw = Gdx.graphics.getWidth();
	public static final int screeny = Gdx.graphics.getHeight();
	public static final float ratiox = screenw/480;
	public static final float ratioy = screeny/800;
	Texture buttonp,buttonopt,bg,optionsb;
	BitmapFont fff;
	Vector3 vec;
	
	public menu(GameStateManager gsm) {
		super(gsm);
		buttonp = new Texture("buttonp.png");
		optionsb = new Texture("optionsb.png");
		bg = new Texture("menubg.png");
		fff = new BitmapFont();
		cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		vec = new Vector3(0,0,0);
		
		}

	@Override
	protected void HandleInput() {
		if(Gdx.input.justTouched()){
			vec.set(Gdx.input.getX(), Gdx.input.getY(),0);
			cam.unproject(vec);
		if(vec.x>MyGdxGame.WIDTH/2-buttonp.getWidth()/2 && vec.x <MyGdxGame.WIDTH/2+buttonp.getWidth()/2 && vec.y>MyGdxGame.HEIGHT/2-buttonp.getHeight()/2 && vec.y <MyGdxGame.HEIGHT/2+buttonp.getHeight()/2){
		gsm.set(new gamestate(gsm));
		}
		if(vec.x>0 && vec.x<350 && vec.y > 40 && vec.y<160 ){
			gsm.set(new options(gsm));
			}
		}	
		
	}

	@Override
	protected void Update(float dt) {
		HandleInput();
		
	}

	@Override
	protected void Render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg, 0, 0, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		sb.draw(buttonp,MyGdxGame.WIDTH/2-buttonp.getWidth()/2,MyGdxGame.HEIGHT/2-buttonp.getHeight()/2);
		sb.draw(optionsb,0,MyGdxGame.WIDTH/2-200,350,120);
		sb.end();
		
	}

	@Override
	protected void Dispose() {
		bg.dispose();
		buttonp.dispose();
		optionsb.dispose();
	}

}
