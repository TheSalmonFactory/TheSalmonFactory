package com.me.thesalmonfactory;

import com.me.thesalmonfactory.helpers.GameContext;

public interface ScreenInterface {
	public void Start();
	public void stop();
	public void Dispose();
	public void Draw(GameContext context);
	public void Update(GameContext context);
}
