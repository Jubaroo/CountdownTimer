
package org.reqiuem.mods.gmchanges.utils;

import com.wurmonline.server.creatures.Communicator;
import org.gotti.wurmunlimited.modloader.interfaces.PlayerMessageListener;
import org.gotti.wurmunlimited.modloader.interfaces.WurmServerMod;

public class Countdown implements WurmServerMod, PlayerMessageListener {

    public static void main (String args[]) throws InterruptedException {
        for (int time = 10;time>=0;time--)
        {
            Thread.sleep(1000);
            System.out.println(time);
        }

    }

    public boolean onPlayerMessage (Communicator communicator, String message) {
        if (message.startsWith("/countdown")) {
            communicator.sendServerMessage("Make it display the time for countdown here", 255, 255, 255);
            //communicator.sendAlertServerMessage("Make it display the time for countdown here");
            return true;
        } else {
            return false;
        }
    }

    public String getVersion() {
        return "v1.0";
    }

}