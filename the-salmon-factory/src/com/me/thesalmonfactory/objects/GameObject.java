package com.me.thesalmonfactory.objects;

import com.me.thesalmonfactory.helpers.GameContext;

public class GameObject extends  Entity {

	public int m_TileSheetID;
	
	public GameObject(int x, int y, int tilesheetID) {
		super(x, y, GameContext.TILE_WIDTH, GameContext.TILE_WIDTH);
		// TODO Auto-generated constructor stub
		m_TileSheetID = tilesheetID;
	}
	
	@Override
	public void Initialize() {
		// TODO Auto-generated method stub
		super.Initialize();
	}

	@Override
	public void Dispose() {
		// TODO Auto-generated method stub
		super.Dispose();
	}

	@Override
	public void Draw(GameContext context) {
		// TODO Auto-generated method stub
		super.Draw(context);
	}

	@Override
	public void Update(GameContext context) {
		// TODO Auto-generated method stub
		super.Update(context);	
	}
}
