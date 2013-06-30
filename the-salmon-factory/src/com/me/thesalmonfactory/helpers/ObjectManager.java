package com.me.thesalmonfactory.helpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.me.thesalmonfactory.objects.*;
import com.me.thesalmonfactory.objects.Entity.EntityState;

public class ObjectManager {
	public List<DragObject> m_GameObjects;
	
	public ObjectManager() {
		// TODO create constructor
		m_GameObjects = new ArrayList<DragObject>();
	}
	
	public void Dispose() {
		Clear();
	}

	public void Draw(GameContext context) {
		//Draw Fishes
		for (DragObject entity : m_GameObjects) {
			if(entity.m_State == EntityState.DEAD) {
				m_GameObjects.remove(entity);
				break;
			}
			if(entity.m_TileSheetID == 0) {
				entity.Draw(context);
			}
		}
		//Draw Robots
		for (DragObject entity : m_GameObjects) {
			if(entity.m_State == EntityState.DEAD) {
				m_GameObjects.remove(entity);
				break;
			}
			if(entity.m_TileSheetID == 1) {
				entity.Draw(context);
			}
		}
	}

	public void Update(GameContext context) {
		for (DragObject entity : m_GameObjects) {
			entity.Update(context);
		}
	}
	
	public void AddObject(DragObject entity) {
		m_GameObjects.add(entity);
	}
	
	public void RemoveObject(DragObject entity) {
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
	
	public void DragObject(User user) {
		//Gdx.app.log("drag?!", "looking for sth to drag...");
		for (DragObject entity : m_GameObjects) {
			if(entity.m_TileSheetID == 1) {
				if(entity.Drag(user)) {
					return;
				}
			}
			//entity.Drag(user);
		}
		
		for (DragObject entity : m_GameObjects) {
			if(entity.m_TileSheetID == 0) {
				if(entity.Drag(user)) {
					return;
				}
			}
			//entity.Drag(user);
		}
	}
	
	public void ResetDragObject(int userID) {
		for (DragObject entity : m_GameObjects) {
			if(entity.UnsetUser(userID)) {
				return;
			}
		}
	}
}
