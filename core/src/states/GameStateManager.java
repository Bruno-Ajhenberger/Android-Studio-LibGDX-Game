package states;

import java.util.Stack;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;



public class GameStateManager {
	
	private Stack<state> states;
	
	public GameStateManager(){
		states = new Stack<state>();
		
	}
	
	public void push(state state){
		states.push(state);
	}

	public void pop(){
		states.pop().Dispose();
	}
	public void set(state state){
		states.pop().Dispose();
		states.push(state);
	}
	public void Update(float dt){
		states.peek().Update(dt);
		
	}
	public void Render(SpriteBatch sb){
		states.peek().Render(sb);
		
	}
	
}
