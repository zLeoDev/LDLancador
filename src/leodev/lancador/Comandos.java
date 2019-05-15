package leodev.lancador;

import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class Comandos extends Command{
	public Main loader;
	public Comandos(Main main) {
		super("lancador");
		this.loader = main;
	}
	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if(sender.hasPermission("lancador.staff")) {
			if(args.length < 2) {
				sender.sendMessage("�eUso:�7 /lancador <�fjogador�7> <�fquantidade�7>");
			} else {
				String jogador = args[0];
				String quantidade = args[1];
				
				if(loader.getServer().getPlayer(jogador) == null) {
					sender.sendMessage("�cEste jogador n�o existe ou n�o est� online!");
				} else {
					Player player = loader.getServer().getPlayer(jogador);
					int quantidades = Integer.parseInt(quantidade);
					loader.getLan(player, quantidades);
					sender.sendMessage("�aVoc� deu �f" + quantidade + " �alan�adores para o jogador �f" + player.getName() + "�a." );
					player.sendMessage("�aVoc� recebeu �f" + quantidade + " �alan�adores");
				}
			}
		}
		return false;
	}

}
