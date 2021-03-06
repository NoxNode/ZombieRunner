package com.bme.zombierunner.entities;

import com.bme.zombierunner.entities.Player;
import com.bme.zombierunner.Settings;
import com.bme.zombierunner.Two;

public class Star {
	public int x, y, time, itime;
	public boolean collected;

	public Star(int x, int y) {
		this.x = x;
		this.y = y;
		this.time = 0;
		this.itime = 0;
		this.collected = false;
	}

	public void Update(Player one, Player two, int img_len, int score) {
		if(Settings.invulnOn) {
			if(!collected && time < Settings.starAppear
					&& score % Settings.starLevel == 0 && score != 0) {
				time++;
				if((one.y < y && Math.sqrt((x - one.x) * (x - one.x)
						+ (y - one.y) * (y - one.y)) < img_len)
						|| (two.y < y && Math.sqrt((x - two.x) * (x - two.x)
								+ (y - two.y) * (y - two.y)) < img_len)) {
					one.invuln = true;
					if(Two.twoPlayer)
						two.invuln = true;
					collected = true;
					itime = 0;
					x = (int) (Math.random() * (Settings.WSize.x - img_len));
					y = (int) (Math.random() * (Settings.WSize.y - img_len));
				}
				if((one.x + img_len > x + (3 * img_len / 20)
						&& one.x < x + img_len - (3 * img_len / 20)
						&& one.y + img_len > y + img_len / 2 && one.y < y
						+ img_len)
						|| (two.x + img_len > x + (3 * img_len / 20)
								&& two.x < x + img_len - (3 * img_len / 20)
								&& two.y + img_len > y + img_len / 2 && two.y < y
								+ img_len)) {
					one.invuln = true;
					if(Two.twoPlayer)
						two.invuln = true;
					collected = true;
					itime = 0;
					x = (int) (Math.random() * (Settings.WSize.x - img_len));
					y = (int) (Math.random() * (Settings.WSize.y - img_len));
				}
			}
			if(one.invuln)
				itime++;
			if(itime > Settings.starATime) {
				itime = 0;
				one.invuln = false;
				if(Two.twoPlayer)
					two.invuln = false;
			}
		}
	}
}