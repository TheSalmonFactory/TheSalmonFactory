package com.me.thesalmonfactory.objects;

import com.me.thesalmonfactory.helpers.GameContext;

public interface EntityInterface {
	public void Initialize();
	public void Dispose();
	public void Draw(GameContext context);
	public void Update(GameContext context);
}
