package leodev.lancador;

import java.util.ArrayList;

import cn.nukkit.Player;
import cn.nukkit.entity.Entity;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.entity.EntityDamageEvent;
import cn.nukkit.event.entity.EntityDamageEvent.DamageCause;
import cn.nukkit.event.player.PlayerInteractEvent;
import cn.nukkit.event.player.PlayerToggleSneakEvent;
import cn.nukkit.item.Item;
import cn.nukkit.level.Sound;
import cn.nukkit.math.Vector3;
import cn.nukkit.plugin.PluginBase;

public class Main extends PluginBase implements Listener{
	ArrayList<Player> voando = new ArrayList<>();
	public void onEnable() {
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void aoUsar(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		Item i = e.getItem();
		Item it = p.getInventory().getItemInHand();
		if(i.getId() == 401 && it.getId() ==  401 && i.getCustomName().equals("�aLan�ador") || it.getCustomName().equals("�aLan�ador")) {
			//if(it.hasEnchantments()) {
				e.setCancelled();
				p.setCheckMovement(false);
				Item item = Item.get(401, 0, 1);
				item.setCustomName("�aLan�ador");
				p.getInventory().removeItem(item);
				Vector3 vec = new Vector3(p.getMotion().getX(), p.getMotion().getY() + 2.2D, p.getMotion().getZ());
				p.setMotion(vec);
				voando.add(p);
				p.getLevel().addSound(vec, Sound.RANDOM_LEVELUP);
			//}
		}
	}
	@EventHandler
	public void aoDano(EntityDamageEvent e) {
		Entity ent  = e.getEntity();
		if(ent instanceof Player) {
			Player p = (Player) e.getEntity();
			if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
				if(voando.contains(p)) {
				e.setCancelled();
				voando.remove(p);
				}
			}
		}
	}
	public void getLan(Player p) {
		Item item = Item.get(401, 0, 1);
		item.setCustomName("�aLan�ador");
		p.getInventory().addItem(item);
	}
	//@EventHandler
	//public void onSneak(PlayerToggleSneakEvent e) {
	//	//a� voc�s fa�am um comando para dar lan�ador//
	//	getLan(e.getPlayer());
	//}
}
