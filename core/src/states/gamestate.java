package states;


import sprites.cars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.MyGdxGame;

public class gamestate extends state {
	
	//public cars cars1;
	Texture car,bg,al,ar,roadani1,roadani2,car2;
	public int carx,x,y,i,end=0,difficulty,difficulty2,cartype=0;
	float score=1,rot;
    Rectangle playercoll;
	private Array<cars> carss;
	Vector2 roadpos;
	BitmapFont brojac;
	Preferences pref;
	Vector3 vec;
	ShapeRenderer sr;
	

	protected gamestate(GameStateManager gsm) {
		super(gsm);
		pref = Gdx.app.getPreferences("data");
		roadpos = new Vector2(0,0);
		brojac = new BitmapFont();
		brojac.setColor(Color.YELLOW);
		car2 = new Texture("carstyle2.png");
		car = new Texture("car.png");
		bg = new Texture("road.png");
		al = new Texture("arrowleft.png");
		ar = new Texture("arrowright.png");
		carx = MyGdxGame.WIDTH/2-37;
		carss = new Array<cars>();
		roadani1= new Texture("roadanimation1.png");
		roadani2= new Texture("roadanimation2.png");
		vec = new Vector3(0,0,0);
		cam.setToOrtho(false, MyGdxGame.WIDTH, MyGdxGame.HEIGHT);
		sr = new ShapeRenderer();
		if(pref.getInteger("carbody")==2){
			car = new Texture("carstyle2.png");
		}
		playercoll = new Rectangle(carx+10,55,55,140);
		for(i=0;i<3;i++){
			carss.add(new cars(i));				
		}
	}

	@Override
	protected void HandleInput() {
		vec.set(Gdx.input.getX(), Gdx.input.getY(),0);
		cam.unproject(vec);
		if(vec.x>0 && vec.x<120 && vec.y>50 && vec.y<170 && Gdx.input.isTouched()){
			if(carx>50){
				carx=carx-5;
			}
		}
		if(vec.x>MyGdxGame.WIDTH-120 && vec.x<MyGdxGame.WIDTH && vec.y>50 && vec.y<170 && Gdx.input.isTouched() ){
			if(carx<MyGdxGame.WIDTH-124){
				carx=carx+5;
				}
		}
	}
	
	@Override
	protected void Update(float dt) {
	HandleInput();
	roadpos.add(0,-200*dt);
	score =(float) (score+score*dt*0.1);	
	playercoll.setPosition(carx+10,55);
	if(roadpos.y<-MyGdxGame.HEIGHT){
		roadpos.set(0,0);
	}
	for(cars cars1 : carss){
		cars1.Update(dt);
		if(cars1.getPos().y<-250){
			cars1.reposition();
		}
	}
	if(end==1){
		gsm.set(new menu(gsm));
	}
	}
	

	@Override
	protected void Render(SpriteBatch sb) {
		sb.setProjectionMatrix(cam.combined);
		sb.begin();
		sb.draw(bg,0,0,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(roadani1,0,roadpos.y,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(roadani2,0,roadpos.y+MyGdxGame.HEIGHT,MyGdxGame.WIDTH,MyGdxGame.HEIGHT);
		sb.draw(car,carx,50,74,150);	
		for(cars cars1 : carss){
			if(end==0){
				sb.draw(cars1.getCars()[cars1.getCar()], cars1.getPos().x, cars1.getPos().y);
		if(cars1.collides(playercoll)){
			end=1;
		}
			}
		}
		sb.draw(al,0,50,120,120);
		sb.draw(ar,MyGdxGame.WIDTH-120,50,120,120);
		brojac.draw(sb, "Score:"+score, 80, 780 );
		sb.end();
	}

	public int getDifficulty() {
		return difficulty;
	}

	@Override
	protected void Dispose() {
		car2.dispose();
		car.dispose();
		bg.dispose();
		al.dispose();
		ar.dispose();
		roadani1.dispose();
		roadani2.dispose();
		sr.dispose();
		brojac.dispose();
		for(cars cars1 : carss){
			car.dispose();
		}
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public void setDifficulty2(int difficulty2) {
		this.difficulty2 = difficulty2;
	}

}
