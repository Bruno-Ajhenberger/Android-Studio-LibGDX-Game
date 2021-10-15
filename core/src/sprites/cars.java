package sprites;

import java.util.Random;

import states.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.values.MeshSpawnShapeValue.Triangle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.MyGdxGame;

public class cars {
	Vector2 pos;
	Texture car1,car2,car3,carstexture;
	Random rng;
	public Rectangle colldet;
	public int randcar,cary=300,carx=100,movspeed=166,diff,car=0;

	Preferences pref;
	private TextureRegion[] cars;
	
	public cars(int x){
		carstexture = new Texture("carsatlas.png");
		cars = new TextureRegion[3];
		cars[0]= new TextureRegion(carstexture,0,0,73,150);
		cars[1]= new TextureRegion(carstexture,73,0,72,150);
		cars[2]= new TextureRegion(carstexture,145,0,48,180);
		rng= new Random();
		pos = new Vector2(50+rng.nextInt(280),MyGdxGame.HEIGHT+250+x*500);
		car1 = new Texture("truck.png");
		colldet = new Rectangle(pos.x,pos.y,100,290);
		pref=Gdx.app.getPreferences("data");
		diff= pref.getInteger("dif1");
	}
	
	public void setMovspeed(int movspeed) {
		this.movspeed = movspeed;
	}

	public void Update(float dt){
		pos.add(0,-movspeed*dt-diff*dt);	
		colldet.setPosition(pos.x, pos.y);
	}
	public void reposition(){
		
		pos.set(50+rng.nextInt(280),MyGdxGame.HEIGHT+250);
		randcar = rng.nextInt(100);
		if(randcar < 33){
			colldet.set(pos.x, pos.y+5, 64,145);
			//car1= new Texture("caryellow.png");
			carx = 74;
			cary = 150;
			car=0;
		}
		if(randcar >33 && randcar <66){
			car1= new Texture("truck.png");
			//colldet.set(pos.x,pos.y+5, 90,290);
			carx = 100;
			cary = 300;
			car=1;
		}
		if(randcar >66){
			car1= new Texture("cabrio.png");
			//colldet.set(pos.x,pos.y+5, 64, 120);
			carx = 74;
			cary = 125;
			car=2;
		}
	}
	public boolean collides(Rectangle player){
		return player.overlaps(colldet);
	}

	public int getCarx() {
		return carx;
	}

	public int getCary() {
		return cary;
	}

	public Vector2 getPos() {
		return pos;
	}

	public Texture getCar1() {
		return car1;
	}

	public Texture getCar2() {
		return car2;
	}

	public Texture getCar3() {
		return car3;
	}
	public int getCar() {
		return car;
	}

	public void dispose() {
		car1.dispose();
		carstexture.dispose();
	}

	public TextureRegion[] getCars() {
		return cars;
	}


	public Texture getCarstexture() {
		return carstexture;
	}

}
