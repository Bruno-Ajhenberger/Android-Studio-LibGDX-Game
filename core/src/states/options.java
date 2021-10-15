package states;





import sprites.cars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.mygdx.game.MyGdxGame;

public class options extends state {
	public static final int screenw = Gdx.graphics.getWidth();
	public static final int screeny = Gdx.graphics.getHeight();
	public static final float ratiox = screenw/480;
	public static final float ratioy = screeny/800;
	SpriteBatch sb;
	Texture radiob1,radiob3, radiob2,returnb,radiob11,radiob22;
	int diff = 0,xpos,ypos;
	BitmapFont dif;
	Preferences pref;
	Vector3 vec;
	

	protected options(GameStateManager gsm) {
		super(gsm);
		sb = new SpriteBatch();
		pref =Gdx.app.getPreferences("data");
		radiob1= new Texture("rb.png");
		radiob11= new Texture("rb.png");
		radiob2= new Texture("rb2.png");
		radiob22= new Texture("rb2.png");
		radiob3= new Texture("rb2.png");
		returnb= new Texture("return.png");
		dif = new BitmapFont();
		dif.setColor(Color.BLACK);
		vec= new Vector3(screenw,screeny,0);
		cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
	}

	@Override
	protected void HandleInput() {
		if(Gdx.input.justTouched()){
			vec.set(Gdx.input.getX(), Gdx.input.getY(),0);
			cam.unproject(vec);
			if(vec.x >45 && vec.x <100 && vec.y > 700 && vec.y < 750 ){
				radiob1= new Texture("rb.png");
				radiob2= new Texture("rb2.png");
				radiob3= new Texture("rb2.png");
				pref.putInteger("dif1", 0);
			}
			if(vec.x >45 && vec.x <100 && vec.y >600 && vec.y < 650 ){
				radiob1= new Texture("rb2.png");
				radiob2= new Texture("rb.png");
				radiob3= new Texture("rb2.png");
				pref.putInteger("dif1", 50);
			}
			if(vec.x >45 && vec.x <100 && vec.y >500 && vec.y< 550 ){
				radiob1= new Texture("rb2.png");
				radiob2= new Texture("rb2.png");
				radiob3= new Texture("rb.png");
				pref.putInteger("dif1", 100);
			}
			if(vec.x>150 && vec.x <165 && vec.y < 430 && vec.y >415){
				radiob11= new Texture("rb.png");
				radiob22= new Texture("rb2.png");
				pref.putInteger("carbody",1);
				
			}
			if(vec.x>150 && vec.x <165 && vec.y > 385 && vec.y <400){
				radiob11= new Texture("rb2.png");
				radiob22= new Texture("rb.png");
				pref.putInteger("carbody",2);
				
			}
			if(vec.x >300 && vec.x <450 && vec.y >150 && vec.y < 225 ){
				gsm.set(new menu(gsm));
			
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
		sb.draw(radiob1, 50, 700,50,50);
		sb.draw(radiob2, 50, 600,50,50);
		sb.draw(radiob3, 50, 500,50,50);
		sb.draw(radiob11, 150, 415,15,15);
		sb.draw(radiob22, 150, 385,15,15);
		dif.draw(sb,"DIFFICULTY : Easy !!!", 120, 730);
		dif.draw(sb,"DIFFICULTY : A bit harder !!!", 120, 630);
		dif.draw(sb,"DIFFICULTY : A lot harder !!!", 120, 530);
		dif.draw(sb,"Car : ", 75,430);
		dif.draw(sb,"Blue ", 180,400);
		dif.draw(sb,"Red ", 180,430);
		sb.draw(returnb,300,150,150,75);
		sb.end();
	}

	@Override
	protected void Dispose() {
	pref.flush();
	sb.dispose();
	radiob1.dispose();
	radiob2.dispose();
	radiob3.dispose();
	dif.dispose();	
	returnb.dispose();
	}

}
