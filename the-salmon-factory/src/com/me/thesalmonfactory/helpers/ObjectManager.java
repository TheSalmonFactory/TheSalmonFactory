package com.me.thesalmonfactory.helpers;

import java.util.ArrayList;
import java.util.List;
import com.me.thesalmonfactory.objects.*;

public class ObjectManager {
	public List<Entity> m_GameObjects;
	
	public ObjectManager() {
		// TODO create constructor
		m_GameObjects = new ArrayList<Entity>();
	}
	
	public void Dispose() {
		Clear();
	}

	public void Draw(GameContext context) {
		for (Entity entity : m_GameObjects) {
			entity.Draw(context);
		}
	}

	public void Update(GameContext context) {
		for (Entity entity : m_GameObjects) {
			entity.Update(context);
		}
	}
	
	public void AddObject(Entity entity) {
		m_GameObjects.add(entity);
	}
	
	public void RemoveObject(Entity entity) {
		m_GameObjects.remove(entity);
	}

	public void Clear() {	
		for (Entity entity : m_GameObjects) {
			entity.Dispose();
		}
		m_GameObjects.clear();
	}
	
	public void ProcessTouchDown(int x, int y, int userID) {
	}
	
	public void ProcessTouchUp(int x, int y, int userID) {
		
	}
	
	public void ProcessTouchDrag(int x, int y, int userID) {
		for (Entity entity : m_GameObjects) {
			if(entity.ExcecuteTouchAction(x, y, userID)) {
				return;
			}
		}
	}
}
