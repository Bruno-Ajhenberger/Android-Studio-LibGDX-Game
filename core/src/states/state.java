package states;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public abstract class state {
	protected OrthographicCamera cam;
	protected GameStateManager gsm;
	protected Vector2 pos;
	
	protected state(GameStateManager gsm){
		this.gsm=gsm;
		cam = new OrthographicCamera();
		pos = new Vector2();
		
	}

	protected abstract void HandleInput();
	protected abstract void Update(float dt);
	protected abstract void Render(SpriteBatch sb);
	protected abstract void Dispose();
}
