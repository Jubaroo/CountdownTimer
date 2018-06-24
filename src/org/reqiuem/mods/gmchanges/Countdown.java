package org.reqiuem.mods.gmchanges;

import com.wurmonline.server.WurmCalendar;
import com.wurmonline.server.players.Player;
import org.gotti.wurmunlimited.modloader.interfaces.Configurable;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Countdown implements WurmServerMod, Configurable {

    long lastPayout;
    private static Logger logger = Logger.getLogger("org.gotti.wurmunlimited.mods.timedpaymod.TimedPayMod");
    private int amountCash;
    private int amountKarma;
    private int payoutInterval;
    private boolean playerMessage;
    private int messageRed;
    private int messageGreen;
    private int messageBlue;

    public void configure(Properties properties) {
        this.amountCash = Integer.parseInt(properties.getProperty("amountCash"));
        this.amountKarma = Integer.parseInt(properties.getProperty("amountKarma"));
        this.payoutInterval = 8 * Integer.parseInt(properties.getProperty("payoutInterval"));
        this.playerMessage = Boolean.parseBoolean(properties.getProperty("playerMessage"));
        this.messageRed = Integer.parseInt(properties.getProperty("red"));
        this.messageGreen = Integer.parseInt(properties.getProperty("green"));
        this.messageBlue = Integer.parseInt(properties.getProperty("blue"));

    }

    public long countdownTimer() {
        long currentTime = WurmCalendar.getCurrentTime();
        if (currentTime > this.lastPayout + (long)this.payoutInterval) {
            logger.log(Level.FINE, "executing payout");
            this.lastPayout = currentTime;
        }
        return currentTime;
    }

    public void addMoneyAndKarma(Player player) {
        try {
            StringBuilder sb = new StringBuilder();
            if (this.amountCash > 0) {
                sb.append("Giving cash ");
                player.addMoney((long)this.amountCash);
                if (playerMessage) { player.getCommunicator().sendServerMessage("You have just received " + amountCash + " iron coins just for being on this server!",messageRed,messageGreen,messageBlue); }
            }

            sb.append("to player " + player.getName());
            logger.log(Level.INFO, sb.toString());
        } catch (IOException var3) {
        }

    }

    public String getVersion() {
        return "v1.0";
    }

}
