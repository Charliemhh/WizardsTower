package Game;

public class Trap {
    private boolean activated;
    private final String activeDescription;
    private final String discoveryDescription;
    private final int trapEffects;

    public Trap(String activationDescription, String discoveryDescription, int trapEffects) {
        this.activated = false;
        this.activeDescription = activationDescription;
        this.discoveryDescription = discoveryDescription;
        this.trapEffects = trapEffects;
    }

    public int trapActivate() {
        this.activated = true;
        return this.trapEffects;
    }

    public int getTrapEffects(){
        return this.trapEffects;
    }

    public String getActiveDescription() {
        return activeDescription;
    }

    public String getDiscoveryDescription() {
        return discoveryDescription;
    }

    public boolean isActivated() {
        return activated;
    }

    public static class TrapCreator {

        public static Trap createTrap(int id) {
            switch (id) {
                case 0://Spike Game.Trap
                    return new Trap(
                            "Spikes suddenly erupt from the ground!"
                            , "A small tripwire leads to a set of spikes, primed to launch.",
                            5);
                case 1://Log Game.Trap
                    return new Trap(
                            "A series of large heavy logs roll towards you, threatening to bowl you over",
                            "A container of heavy logs threatens to be unleashed if one steps on the wrong panel.",
                            8);
            }
            return null;
        }
    }
}